package com.best.practice.ai.controller.loop;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// 最小 Agent Loop Demo：SSE 推送 token/tool_start/tool_end/done
@RestController
@RequestMapping("/api/agent")
public class AgentLoopController {

    private final ChatClient chatClient;
    private final Map<String, Function<Map<String, Object>, Map<String, Object>>> tools = new HashMap<>();

    public AgentLoopController(ChatClient.Builder builder) {
        this.chatClient = builder.build();

        // 注册一个演示工具：getWeather
        tools.put("getWeather", args -> {
            String city = String.valueOf(args.getOrDefault("city", "北京"));
            Map<String, Object> result = new HashMap<>();
            result.put("city", city);
            result.put("weather", "晴");
            result.put("tempC", 26);
            return result;
        });
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestParam String question) {
        return Flux.create(sink -> {
            try {
                // 这里简化成最多两轮，方便理解 loop
                List<Map<String, String>> history = new ArrayList<>();
                history.add(msg("system", "你是一个助手。能判断是否需要天气工具。"));
                history.add(msg("user", question));

                // === Round 1: 先让模型回答（流式）===
                StringBuilder round1Text = new StringBuilder();

                chatClient.prompt()
                        .user(buildPrompt(history, true))
                        .stream()
                        .content()
                        .doOnNext(token -> {
                            round1Text.append(token);
                            sink.next(sse("token", token));
                        })
                        .doOnComplete(() -> {
                            String text = round1Text.toString();

                            // 简化策略：如果包含“天气/温度/下雨”等关键词，则触发工具
                            boolean needTool = needWeatherTool(question, text);

                            if (!needTool) {
                                sink.next(sse("done", "no_tool"));
                                sink.complete();
                                return;
                            }

                            // === Tool Start ===
                            Map<String, Object> args = new HashMap<>();
                            args.put("city", extractCity(question));
                            sink.next(sse("tool_start", toJson(Map.of(
                                    "name", "getWeather",
                                    "args", args
                            ))));

                            // 执行工具
                            Map<String, Object> toolResult = tools.get("getWeather").apply(args);

                            // === Tool End ===
                            sink.next(sse("tool_end", toJson(Map.of(
                                    "name", "getWeather",
                                    "result", toolResult
                            ))));

                            // === Round 2: 带工具结果继续流式输出 ===
                            String followup = """
                                    用户问题：%s
                                    工具结果：%s
                                    请基于工具结果，给出简洁中文答复。
                                    """.formatted(question, toJson(toolResult));

                            chatClient.prompt()
                                    .user(followup)
                                    .stream()
                                    .content()
                                    .doOnNext(token2 -> sink.next(sse("token", token2)))
                                    .doOnComplete(() -> {
                                        sink.next(sse("done", "ok"));
                                        sink.complete();
                                    })
                                    .doOnError(e2 -> {
                                        sink.next(sse("error", e2.getMessage()));
                                        sink.error(e2);
                                    })
                                    .subscribe();
                        })
                        .doOnError(e -> {
                            sink.next(sse("error", e.getMessage()));
                            sink.error(e);
                        })
                        .subscribe();

            } catch (Exception e) {
                sink.next(sse("error", e.getMessage()));
                sink.error(e);
            }
        });
    }

    private static Map<String, String> msg(String role, String content) {
        Map<String, String> m = new HashMap<>();
        m.put("role", role);
        m.put("content", content);
        return m;
    }

    private static String buildPrompt(List<Map<String, String>> history, boolean streamPhase) {
        StringBuilder sb = new StringBuilder();
        for (Map<String, String> m : history) {
            sb.append(m.get("role")).append(": ").append(m.get("content")).append("\n");
        }
        if (streamPhase) {
            sb.append("如果你认为需要实时天气信息，先简短说明你将查询天气。");
        }
        return sb.toString();
    }

    private static boolean needWeatherTool(String question, String draft) {
        String s = (question + " " + draft).toLowerCase();
        return s.contains("天气") || s.contains("温度") || s.contains("下雨") || s.contains("weather");
    }

    private static String extractCity(String question) {
        if (question.contains("北京")) return "北京";
        if (question.contains("上海")) return "上海";
        if (question.contains("深圳")) return "深圳";
        return "北京";
    }

    // SSE 文本块（简单格式）
    private static String sse(String event, String data) {
        return "event: " + event + "\n" +
                "data: " + data.replace("\n", "\\n") + "\n\n";
    }

    // 极简 JSON（demo用）
    private static String toJson(Map<String, ?> map) {
        StringBuilder sb = new StringBuilder("{");
        int i = 0;
        for (Map.Entry<String, ?> e : map.entrySet()) {
            if (i++ > 0) sb.append(",");
            sb.append("\"").append(e.getKey()).append("\":");
            Object v = e.getValue();
            if (v instanceof String) {
                sb.append("\"").append(v).append("\"");
            } else if (v instanceof Map<?, ?> vm) {
                sb.append("{");
                int j = 0;
                for (Map.Entry<?, ?> me : vm.entrySet()) {
                    if (j++ > 0) sb.append(",");
                    sb.append("\"").append(me.getKey()).append("\":");
                    Object mv = me.getValue();
                    if (mv instanceof String) sb.append("\"").append(mv).append("\"");
                    else sb.append(mv);
                }
                sb.append("}");
            } else {
                sb.append(v);
            }
        }
        sb.append("}");
        return sb.toString();
    }
}

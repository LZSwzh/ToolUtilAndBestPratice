package com.best.practice.ai.controller.chat;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class SpringAIChatController {
    private final ChatClient chatClient;

    /**
     * 官方示例不建议直接注入
     * @param chatClientBuilder
     */
    public SpringAIChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/spring/chat/zhipu")
    public String chatBasic(@RequestParam("userInput") String userInput) {
        log.info("chat basic begin,userInput:{}",userInput);
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }

    @PostMapping("/spring/chat-formatted/zhipu")
    public ActorFilms chatBasicFormatted(@RequestParam("userInput") String userInput) {
        log.info("chat basic formatted begin,userInput:{}",userInput);
        ChatClient.CallResponseSpec spec = this.chatClient.prompt()
                .system("")
                .user(userInput)
                .call();
        log.info("chat basic formatted begin,spec:{}", JSON.toJSONString(spec));
        //entity方法，一种结构化输出的能力，SpringAI自动在prompt中拼接一段BeanOutputConverter，强制要求返回对应结构的指令，
        // 提示词示例如下，如果你是普通的闲聊，会强行编造一个符合schema的JSON
        /*
          Your response should be in JSON format.
          ...
          Here is the JSON Schema instance your output must adhere to:
          {"type":"object","properties":{"actor":{"type":"string"},"movies":{"type":"array","items":{"type":"string"}}}}
        */
        return spec.entity(ActorFilms.class);
    }

    /**
     * ChatClient 的 stream() 方法调用后，响应类型有以下处理选项：
     * - Flux<String> content()：返回 AI 模型生成字符串的 Flux 流。
     * - Flux<ChatResponse> chatResponse()：返回包含响应元数据的 ChatResponse 对象 Flux 流。
     * - Flux<ChatClientResponse> chatClientResponse()：返回包含 ChatResponse 对象和 ChatClient 执行上下文的 ChatClientResponse 对象 Flux 流，
     * 可访问 Advisor 执行期间的附加数据（如 RAG 流程检索的相关文档）
     */
    /**
     * produces ：用来声明 接口返回内容的 MIME 类型
     * 1. 告诉客户端"我返回的是什么格式"： Spring 会把这个值写到响应头 Content-Type 里：
     * 2. 参与请求路由匹配（基于 Accept 头）：如果客户端请求带了 Accept 头，Spring 会拿它和 produces 做匹配：
     *   一样的直接命中。如果不一样会返回406，让同一个url根据accept头分发到不同方法做内容协商
     *
     *   │ application/json     │ MediaType.APPLICATION_JSON_VALUE   │ 默认值，REST 接口最常用         │
     *   ├──────────────────────┼────────────────────────────────────┼─────────────────────────────────┤
     *   │ text/event-stream    │ MediaType.TEXT_EVENT_STREAM_VALUE  │ SSE 流式输出，关键场景          │
     *   ├──────────────────────┼────────────────────────────────────┼─────────────────────────────────┤
     *   │ application/x-ndjson │ MediaType.APPLICATION_NDJSON_VALUE │ 流式 JSON（每行一个 JSON 对象） │
     *   ├──────────────────────┼────────────────────────────────────┼─────────────────────────────────┤
     *   │ text/plain           │ MediaType.TEXT_PLAIN_VALUE         │ 纯文本                          │
     *   ├──────────────────────┼────────────────────────────────────┼─────────────────────────────────┤
     *   │ text/html            │ MediaType.TEXT_HTML_VALUE          │ HTML 页面                       │
     * @param userInput
     * @return
     */
    @GetMapping(value = "/spring/stream/zhipu",produces = "text/plain;charset=UTF-8")
    public Flux<String> chatStream(@RequestParam("userInput") String userInput){
        log.info("chat stream begin...");
        return chatClient.prompt()
                .user(userInput)
                .stream()
                .content();
    }

    @GetMapping(value = "/spring/stream-formatted/zhipu")
    public List<ActorFilms> chatStreamFormatted(@RequestParam("userInput") String userInput){
        log.info("chat stream begin...");
        var converter = new BeanOutputConverter<>(new ParameterizedTypeReference<List<ActorFilms>>() {});

        Flux<String> flux = this.chatClient.prompt()
                .user(u -> u.text("""
                        Generate the filmography for a random actor.
                        {format}
                      """)
                        .param("format", converter.getFormat()))
                .stream()
                .content();
        //阻塞流,拼接内容
        String content = flux.collectList().block().stream().collect(Collectors.joining());
        //转换为实体
        List<ActorFilms> actorFilms = converter.convert(content);
        return actorFilms;
    }

    /**
     * 声明一个record用于充当请求响应String的映射结构
     * @param actor
     * @param movies
     */
    public record ActorFilms(String actor, List<String> movies) {}




}

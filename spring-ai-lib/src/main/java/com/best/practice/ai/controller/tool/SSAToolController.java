package com.best.practice.ai.controller.tool;

import com.alibaba.fastjson2.JSON;
import com.best.practice.ai.service.tool.SSAFunctionToolService;
import com.best.practice.ai.service.tool.SSAMethodToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <1>.工具调用的方式包括两大类
 *   方法即工具MethodToolCallback: 包括@Tool直接定义的声明式和手动反射创建MethodToolCallback的编程式方法即工具调用
 *   函数即工具FunctionToolCallback: 包括@Bean直接定义的声明式和手动创建FunctionToolCallback的编程式函数即工具调用
 * <2>.
 */
@Slf4j
@RestController
public class SSAToolController {

    private final SSAMethodToolService ssaMethodToolService;

    private final ChatClient client;
    private final ChatModel chatModel;

    private final SSAFunctionToolService ssaFunctionToolService;

    public SSAToolController(SSAMethodToolService ssaMethodToolService,
                             SSAFunctionToolService ssaFunctionToolService,
                             ChatClient.Builder builder,
                             ChatModel chatModel
    ) {
        this.ssaMethodToolService = ssaMethodToolService;
        this.client = builder.build();
        this.chatModel = chatModel;
        this.ssaFunctionToolService = ssaFunctionToolService;
    }
    /* ============================================= 【声明式】方法即工具 ============================================= */

    /**
     * ChatClient调用【声明式】方法即工具
     */
    @GetMapping("/tool/method/client/declarative")
    public void toolStatementByClient(){
        SimpleLoggerAdvisor customLogger = new SimpleLoggerAdvisor(
                request -> "Custom request: " + JSON.toJSONString(request),
                response -> "Custom response: " + JSON.toJSONString(response),
                1
        );
        ChatResponse response = client
                .prompt()
                .advisors(customLogger)
                .user("今天的天气怎么样")
                .tools(ssaMethodToolService)
                .toolContext(Map.of("date", "2026-01-01"))
                .call()
                .chatResponse();
        log.info("[TOOL][STATEMENT] chatClient响应:{}",response.getResult());
        /*
            调用链真相
          ChatClient.call()
            └── Advisor 链（SimpleLoggerAdvisor 在这里）  ← 你看到的 request/response
                  └── ChatModel.call()                    ← 工具循环在这里面
                        ├── 第 1 次 HTTP → 模型返回 toolCalls=[getWeather]，finishReason=TOOL_CALLS
                        ├── ToolCallingManager 执行工具 → 你看到的"触发工具调用"
                        ├── 第 2 次 HTTP → 把工具结果回传，模型返回最终文本
                        └── 把最终响应（toolCalls=[], finishReason=STOP）返回给 Advisor

          ToolCallingChatOptions.internalToolExecutionEnabled 默认是 true，整个 tool loop 在 ZhiPuAiChatModel 内部完成。Advisor 只看到循环外面的最终一帧——而最终那一帧本来就不该再有 toolCalls，否则循环没结束。

          所以日志现象完全合理：
          - 日志里 toolCalls: []、finishReason: STOP —— 这是 第 2 次 API 调用的返回
          - "触发工具调用..." —— 是中间那一帧被消费时打的
          - 你"看不见"的，是中间那次 HTTP 里 toolCalls=[{name:"dateTool",...}] 的报文

           解决办法：
           spring:
              ai:
                zhipuai:
                  model-name: 'glm-5'
                  api-key: ${ZHIPUAI_API_KEY}
                chat:
                  observations:
                    log-prompt: true        # 把 prompt 内容打到 observation/span 里
                    log-completion: true    # 把模型回复打到 observation/span 里
            logging:
              level:
                org.springframework.web.client.RestClient: DEBUG
                org.springframework.ai.chat.client.advisor: DEBUG
                org.springframework.ai.model.tool: DEBUG
         */
    }

    /**
     * ChatModel调用【声明式】方法即工具
     */
    @GetMapping("/tool/method/model/declarative")
    public void toolStatementByModel(){
        // 1. 把 @Tool 注解的 Bean 转成 ToolCallback 数组
        ToolCallback[] tools = ToolCallbacks.from(ssaMethodToolService);

        // 2. 把 tools 塞进 ChatOptions
        ToolCallingChatOptions toolOptions = ToolCallingChatOptions.builder()
                .toolCallbacks(tools)
                .toolContext(Map.of("date","20260101"))
                // .internalToolExecutionEnabled(true)  // 默认就是 true,工具循环由框架内部完成
                .build();

        // 3. Prompt = 消息列表 + 选项
        Prompt prompt = Prompt.builder()
                .messages(new UserMessage("今天的天气怎么样"))
                .chatOptions(toolOptions)
                .build();

        // 4. 直接调用模型,框架内部会自动完成 tool calling 多轮循环
        ChatResponse response = chatModel.call(prompt);

        log.info("[TOOL][STATEMENT] chatModel响应:{}", response.getResult().getOutput().getText());
    }

    /* ============================================= 【编程式】方法即工具 ============================================= */

    /**
     * ChatClient调用【编程式】方法即工具
     */
    @GetMapping("tool/method/client/imperative")
    public void toolCoderByClient(){
        //构造工具回调ToolCallback,MethodToolCallback是方法即工具范式的回调
        MethodToolCallback currentTimeCbk = ssaMethodToolService.getCurrentTimeCbk();
        ChatResponse response = client
                .prompt()
                .user("现在几点了")
                .toolCallbacks(currentTimeCbk)
                .toolContext(Map.of("date","20260101"))
                .call()
                .chatResponse();
        log.info("[TOOL][CODER] CLIENT RESP:{}",response.getResult().getOutput().getText());
    }

    /**
     * chatModal调用【编程式】方法即工具
     */
    @GetMapping("tool/method/model/imperative")
    public void toolCoderByModel(){
        //构造工具回调ToolCallback,MethodToolCallback是方法即工具范式的回调
        MethodToolCallback currentTimeCbk = ssaMethodToolService.getCurrentTimeCbk();
        ChatResponse response = client
                .prompt()
                .user("现在几点了")
                .toolCallbacks(currentTimeCbk)
                .toolContext(Map.of("date","20260101"))
                .call()
                .chatResponse();
        log.info("[TOOL][CODER] CLIENT RESP:{}",response.getResult().getOutput().getText());
    }
    /* ============================================= 【声明式】函数即工具 ============================================= */

    /**
     * ChatClient调用【声明式】函数即工具
     */
    @GetMapping("/tool/function/client/declarative")
    public void toolFuncDeclareByClient(){
//        Map<String, Object> context = toolContext.getContext();
//        context.computeIfAbsent("date",(k)->"20260101");
        String content = ChatClient.create(chatModel)
                .prompt("洛阳这座城市的具体信息是什么?")
                .toolNames("cityFunctionTool")
                .toolContext(Map.of("date", "2026-01-01"))
                .call()
                .content();
        log.info("[TOOL][FUNC] CLIENT 声明式响应:{}",content);
    }

    /* ============================================= 【编程式】函数即工具 ============================================= */

    /**
     * ChatClient调用函数即工具:编程式
     */
    @GetMapping("/tool/function/client/imperative")
    public void toolFunctionByClient(){
        //构造工具回调ToolCallback,MethodToolCallback是方法即工具范式的回调
        ToolCallback weatherFunctionTool = ssaFunctionToolService.getWeatherFunctionTool();
        ChatResponse response = client
                .prompt()
                .user("今天北京的天气怎么样")
                .toolCallbacks(weatherFunctionTool)
                .toolContext(Map.of("date","2026-01-01"))
                .call()
                .chatResponse();
        log.info("[TOOL][FUNC] CLIENT RESP:{}",response.getResult().getOutput().getText());
    }


    /**
     * ChatModel调用函数即工具：编程式
     */
    @GetMapping("/tool/function/model/imperative")
    public void toolFunctionByModel() {
        // 构造工具回调ToolCallback, MethodToolCallback是方法即工具范式的回调
        ToolCallback weatherFunctionTool = ssaFunctionToolService.getWeatherFunctionTool();
        ToolCallingChatOptions opt = ToolCallingChatOptions.builder().toolCallbacks(weatherFunctionTool).build();
        Prompt prompt = Prompt.builder()
                .messages(new UserMessage("今天北京的天气怎么样"))
                .chatOptions(opt)
                .build();
        String text = chatModel.call(prompt).getResult().getOutput().getText();
        log.info("[TOOL][FUNC] MODEL RESP:{}", text);
    }


}

package com.best.practice.ai.controller.chat;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
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

    /**
     *  ChatClient 的 call() 方法调用后返回CallResponseSpec，这个响应有几种处理方式
     *  - content():直接获取到模型输出的内容
     *  - entity(): 将模型输出的内容自动格式化为entity
     *  - chatResponse(): 输出更加丰富的内容：有关响应如何生成的元数据，并且还可以包含多个响应，称为 Generation，每个响应都有其自己的元数据
     *  - chatClientResponse():返回ChatClientResponse 对象，该对象包含 ChatResponse 对象和 ChatClient 执行上下文，使您可以访问 Advisor 执行期间使用的额外数据（例如，RAG 流中检索到的相关文档）。
     *  - responseEntity():ChatResponse 和 Java 类型。当您需要在一次调用中同时访问完整的 AI 模型响应（带元数据和生成）和结构化输出实体时
     */
    /**
     * <1>.content()方法,直接返回模型输出的文本
     * @param userInput 用户提问
     * @return 模型同步输出
     */
    @PostMapping("/spring/chat/zhipu")
    public String chatBasic(@RequestParam("userInput") String userInput) {
        log.info("chat basic begin,userInput:{}",userInput);
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }

    /**
     * <2>.entity方法，一种结构化输出的能力，SpringAI自动在prompt中拼接一段BeanOutputConverter，强制要求返回对应结构的指令，
     *  提示词示例如下，如果你是普通的闲聊，会强行编造一个符合schema的JSON
     *  Your response should be in JSON format.
     *  ...
     *  Here is the JSON Schema instance your output must adhere to:
     *  {"type":"object","properties":{"actor":{"type":"string"},"movies":{"type":"array","items":{"type":"string"}}}}
     *
     * @param userInput 用户提问
     * @return 要求输出的具体格式化实体
     */
    @PostMapping("/spring/chat-formatted/zhipu")
    public ActorFilms chatBasicFormatted(@RequestParam("userInput") String userInput) {
        log.info("chat basic formatted begin,userInput:{}",userInput);
        ChatClient.CallResponseSpec spec = this.chatClient.prompt()
                .system("")
                .user(userInput)
                .call();
        log.info("chat basic formatted begin,spec:{}", JSON.toJSONString(spec));
//        也可以通过这个方法返回ResponseEntity
//        ResponseEntity<ChatResponse, ActorFilms> reponseEntity = this.chatClient.prompt()
//                .system("")
//                .user(userInput)
//                .call()
//                .responseEntity(ActorFilms.class);
        return spec.entity(ActorFilms.class);
    }

    /**
     * <3>.chatResponse()方法，可以输出更加详细的结构，结构代表意义如下：
     * 1. result：主结果（单条）
     *   - result.metadata：
     *      finishReason："STOP"：模型正常停止，不是长度截断或异常中断。
     *      contentFilters: []：内容过滤命中列表，这里为空表示无命中记录
     *  - result.output: “模型输出消息本体”，可理解成 Spring AI 的 AssistantMessage/Generation 对应序列化结果
     *     messageType: 'ASSISTANT':  消息类型
     *     metadata:    这条消息自己的元信息
     *     toolCalls:  工具调用列表；为空表示这次没有 function calling / tool use。
     *     media:      多模态媒体返回；为空表示纯文本。
     *     text:       最终自然语言文本
     * 2. metadata：本次调用级别统计信息
     * 3. results：候选结果列表（多候选）
     * @param userInput
     * @return
     */
    @PostMapping("/spring/chat-response/zhipu")
    public ChatResponse chatBasicResponse(@RequestParam("userInput") String userInput){
        ChatResponse chatResponse = this.chatClient.prompt()
                .user(userInput)
                .call()
                .chatResponse();
        log.info("chatBasicResponse 输出:{}",JSON.toJSONString(chatResponse));
        /*
         {
          "result": {
            "metadata": {
              "finishReason": "STOP",
              "contentFilters": [
              ],
              "empty": true
            },
            "output": {
              "messageType": "ASSISTANT",
              "metadata": {
                "finishReason": "STOP",
                "id": "20260510151312d693136fd9004f71",
                "role": "ASSISTANT",
                "messageType": "ASSISTANT"
              },
              "toolCalls": [
              ],
              "media": [
              ],
              "text": "早上好！☀️ 希望你今天过得愉快！有什么我可以帮你的吗？"
            }
          },
          "metadata": {
            "id": "20260510151312d693136fd9004f71",
            "model": "glm-4-air",
            "rateLimit": {
              "requestsRemaining": 0,
              "tokensRemaining": 0,
              "requestsReset": "PT0S",
              "tokensLimit": 0,
              "requestsLimit": 0,
              "tokensReset": "PT0S"
            },
            "usage": {
              "promptTokens": 11,
              "completionTokens": 21,
              "totalTokens": 32,
              "nativeUsage": {
                "completion_tokens": 21,
                "prompt_tokens": 11,
                "total_tokens": 32
              }
            },
            "promptMetadata": [
            ],
            "empty": false
          },
          "results": [
            {
              "metadata": {
                "finishReason": "STOP",
                "contentFilters": [
                ],
                "empty": true
              },
              "output": {
                "messageType": "ASSISTANT",
                "metadata": {
                  "finishReason": "STOP",
                  "id": "20260510151312d693136fd9004f71",
                  "role": "ASSISTANT",
                  "messageType": "ASSISTANT"
                },
                "toolCalls": [
                ],
                "media": [
                ],
                "text": "早上好！☀️ 希望你今天过得愉快！有什么我可以帮你的吗？"
              }
            }
          ]
        }
         */
        if (chatResponse != null) {
            // 输出的是results区域，也就是候选结果集
            log.info("chatBasicResponse 输出generations:{}",JSON.toJSONString(chatResponse.getResults()));
            // 输出的是顶层的result区域,也就主结果
            log.info("chatBasicResponse 输出generations[0]:{}",JSON.toJSONString(chatResponse.getResult()));
            // 输出的是顶层的metadata区域，本次调用级别统计信息
            log.info("chatBasicResponse 输出chatResponseMetadata:{}",JSON.toJSONString(chatResponse.getMetadata()));
        }

        return chatResponse;
    }

    /**
     * ChatClient 的 stream() 方法调用后，响应类型有以下处理选项：
     * - Flux<String> content()：返回 AI 模型生成字符串的 Flux 流。
     * - Flux<ChatResponse> chatResponse()：返回包含响应元数据的 ChatResponse 对象 Flux 流。
     * - Flux<ChatClientResponse> chatClientResponse()：返回包含 ChatResponse 对象和 ChatClient 执行上下文的 ChatClientResponse 对象 Flux 流，
     * 可访问 Advisor 执行期间的附加数据（如 RAG 流程检索的相关文档）
     */

    /**
     * <1>.content():直接生成内容对应的String的Flux流
     *  produces ：用来声明 接口返回内容的 MIME 类型
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

    /**
     * <2>.chatResponse()：返回包含响应元数据的 ChatResponse 对象 Flux 流。
     * @param userInput 用户输入提示词
     * @return 返回ChatResponse对应Flux流
     * Spring MVC 处理 Flux 的逻辑：
     * 1. 检测到返回值是 Flux<T>
     * 2. 检查 produces：
     *    - 如果是 TEXT_EVENT_STREAM → 逐条输出，每条用 SSE 格式
     *    - 如果是其他类型 → 尝试收集全部数据到 CollectedValuesList；
     * 3. 然后找 HttpMessageConverter 序列化最终结果
     */
    @GetMapping(value = "/spring/stream-response/zhipu", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> chatStreamFormatted(@RequestParam("userInput") String userInput){
        log.info("chat stream response begin...");
        return this.chatClient
                .prompt(userInput)
                .stream()
                .chatResponse();
    }

    /**
     * <3>.chatClientResponse():返回ChatResponse对象和执行上下文的 ChatClientResponse 对象
     * @param userInput 用户提示词
     * @return 返回ChatResponse对象和执行上下文的 ChatClientResponse 对象
     */
    @GetMapping(value = "/spring/stream-client-response/zhipu", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatClientResponse> chatStreamClientResp(@RequestParam("userInput") String userInput){
        return this.chatClient
                .prompt(userInput)
                .stream()
                .chatClientResponse();
    }

    /**
     * 声明一个record用于充当请求响应String的映射结构
     * @param actor
     * @param movies
     */
    public record ActorFilms(String actor, List<String> movies) {}




}

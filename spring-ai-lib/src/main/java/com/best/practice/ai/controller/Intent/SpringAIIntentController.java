package com.best.practice.ai.controller.Intent;

import com.best.practice.ai.Intent.IntentResult;
import com.best.practice.ai.Intent.IntentValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SpringAIIntentController {

    private final ChatClient chatClient;

    public SpringAIIntentController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * 结构化设计方案1：单 record + enum + Ma
     * 优点：简单、通用、稳定
     * 缺点： slots 类型不强，需要业务侧再校验
     * @return 意图识别结果,【方案1设计的结构化实体】
     */
    @PostMapping("/spring/intent/recognizeIntent")
    public IntentResult recognizeIntent(@RequestParam("message")String message){
        //意图识别必须用 SystemPrompt 框定模型角色和规则，光靠 schema 不够稳。建议结构：
        String system = """
          你是一个意图识别助手。请根据用户输入，从下列预定义意图中选择一个最匹配的：
        
          - QUERY_WEATHER: 用户想查询天气，slots 可包含 city、date
          - BOOK_FLIGHT: 用户想订机票，slots 可包含 from、to、date
          - PLAY_MUSIC: 用户想播放音乐，slots 可包含 song、artist
          - SMALL_TALK: 闲聊、问候、感谢等无明确业务意图的对话
          - UNKNOWN: 无法归入以上任何类别
          <HARD>
          规则：
          1. 必须严格从上述意图中选择，不要创造新意图。
          2. 槽位只填用户明确提到的，未提到的不要编造。
          3. confidence 反映你对判断的把握程度（0-1）。
          4. intent=SMALL_TALK 或 UNKNOWN 时，在 fallbackReply 里给出自然的中文回复；其他情况 fallbackReply 留空字符串。
          </HARD>
          """;
        IntentResult result = chatClient.prompt()
                .system(system)
                .user(message)
                .call()
                .entity(IntentResult.class);
        /**
         * 业务侧校验1：模型输出校验,避免模型胡来
         */
        // 兜底：模型返回 null 或 confidence 异常
        if (result == null || result.intent() == null) {
            return IntentResult.unknown(message, "未能理解您的意图，请换种表达");
        }
        if (result.confidence() < 0.6) {
            log.warn("低置信度意图识别: input={}, intent={}, conf={}",message, result.intent(), result.confidence());
            return IntentResult.unknown(message, "不太确定您的意思，能再具体些吗？");
        }
        /**
         * 业务侧校验2：判定意图的必槽位，校验失败就反问用户：
         * eg:用户想查询天气，slots 可包含 city、date  ,我觉得这个city必填，那么就校验，缺少就询问用户
         */
        IntentValidation vali = IntentResult.validate(result);
        boolean flag = vali.validFlag();
        if (!flag){
            //TODO 需要询问用户澄清slot必填槽
            System.out.println("缺少关键参数");
        }
        return result;
    }
}

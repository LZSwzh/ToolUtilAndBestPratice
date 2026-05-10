package com.best.practice.ai.controller.Intent;

import com.best.practice.ai.model.Intent.IntentResult;
import com.best.practice.ai.model.Intent.IntentValidation;
import com.best.practice.ai.service.intent.IntentClassifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpringAIIntentController {

    private final IntentClassifyService intentClassifyService;

    /**
     * 结构化设计方案1：单 record + enum + Ma
     * 优点：简单、通用、稳定
     * 缺点： slots 类型不强，需要业务侧再校验
     * @return 意图识别结果,【方案1设计的结构化实体】
     */
    @PostMapping("/spring/intent/recognizeIntent")
    public IntentResult recognizeIntent(@RequestParam("message")String message){
        return intentClassifyService.recognizeIntent(message);
    }
}

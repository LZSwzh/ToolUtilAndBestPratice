package com.best.practice.ai.controller.chat;

import com.best.practice.ai.service.ZhiPuNativeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

/**
 * 对接原生的SDK完成基本的对话
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class NativeChatController {

    private final ZhiPuNativeService zhiPuNativeService;

    @PostMapping("/native/chat/zhipu")
    public void chatBasic(String question){
        log.info("Basic Chat begin,zhipu sdk question:{}",question);
        zhiPuNativeService.basicChatByZp("glm-5",question);
    }
    @PostMapping("/native/stream/zhipu")
    public void chatStream(String question){
        log.info("Stream Chat begin,zhipu sdk question:{}",question);
        zhiPuNativeService.streamChatByZp("glm-5",question);
    }

    @PostMapping("/native/function/zhipu")
    public void chatWithFunc(String toolName){
        log.info("execute function calling...");
        zhiPuNativeService.functionCall(toolName);
    }

    @PostMapping("/native/multimodal/zhipu")
    public void chatWithFile(){
        log.info("execute function calling begin");
        try {
            zhiPuNativeService.multiModal();
        } catch (IOException e) {
            log.error("execute function calling error",e);
        }
    }
}

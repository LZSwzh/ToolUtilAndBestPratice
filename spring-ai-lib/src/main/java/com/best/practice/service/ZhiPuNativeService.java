package com.best.practice.service;

import ai.z.openapi.ZhipuAiClient;
import ai.z.openapi.core.Constants;
import ai.z.openapi.service.model.*;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class ZhiPuNativeService {

    private final ZhipuAiClient zhipuAiClient;

    private final NativeToolMockService nativeToolMockService;

    public void basicChatByZp(){
        basicChatByZp("glm-5");
    }
    public void basicChatByZp(String modelName){
        basicChatByZp(modelName,"你好，你是谁");
    }

    /**
     *  回答格式如下,包括：内容、思考过程、角色等信息
     * {
     *     "content": "你好！我是**X科技有限公司**开发的个人助手。\n\n我擅长回答**财务**、**考勤**以及**编程**领域的相关问题。无论是处理报销流程、解决考勤异常，还是协助编写代码，我都能为您提供帮助。\n\n请问今天有什么可以帮您的吗？",
     *     "reasoning_content": "1. **分析用户输入：**\n    *   用户说：“你好，你是谁”。\n    *   意图：用户想要知道我的身份/角色。\n\n2. **分析我的角色/人设：**\n    *   系统提示词定义：“你是X科技有限公司开发的个人助手”。\n    *   关键技能：“你擅长回答财务、考勤、编程领域的相关助手”。\n\n3. **构思回复：**\n    *   *问候：* 回应问候（例如，“你好！”）。\n    *   *身份：* 根据提示词清晰地陈述我是谁（例如，“我是X科技有限公司开发的个人助手”）。\n    *   *能力：* 提及我的具体专长（财务、考勤、编程）。\n    *   *行动号召/帮助提议：* 询问我能如何帮助用户。\n\n4. **起草回复（内心独白/尝试）：**\n    *   *草稿 1：* 你好，我是X科技有限公司开发的个人助手。我擅长财务、考勤和编程。\n    *   *草稿 2（更专业）：* 您好！我是X科技有限公司开发的个人助手。我主要擅长处理财务、考勤以及编程领域的问题。请问有什么可以帮您的吗？\n    *   *根据具体约束进行完善：* 提示词特别提到了领域。我应该强调它们。\n\n5. **最终润色（中文）：**\n    你好！我是**X科技有限公司**开发的个人助手。\n\n    我擅长回答**财务**、**考勤**以及**编程**领域的相关问题。无论是报销流程、打卡异常，还是代码编写与技术难题，都可以随时向我提问。\n\n    请问有什么我可以帮您的吗？\n\n6. **最终输出生成：**（与起草的回复一致）。",
     *     "role": "assistant"
     * }
     * @param modelName 模型名称
     * @param userPrompt 用户提示词
     */
    public void basicChatByZp(String modelName,String userPrompt){
        // 构造系统级prompt
        ChatMessage systemMessage = ChatMessage.builder()
                .role(ChatMessageRole.SYSTEM.value())
                .content("你是X科技有限公司开发的个人助手,你擅长回答财务、考勤、编程领域的相关助手")
                .build();
        // 构造用户级prompt
        ChatMessage userMessage = ChatMessage.builder()
                .role(ChatMessageRole.USER.value())
                .content(userPrompt)
                .build();
        // 发起基本的聊天
        ChatCompletionCreateParams request = ChatCompletionCreateParams.builder()
                .model("glm-5")
                .messages(Arrays.asList(systemMessage, userMessage))
                .build();
        // 发送请求完成对话补全
        ChatCompletionResponse response = zhipuAiClient.chat().createChatCompletion(request);

        if (response.isSuccess()){
            ChatMessage reply = response.getData().getChoices().get(0).getMessage();
            log.info("智谱SDK回复内容为：{}", JSON.toJSONString(reply));
        }else{
            log.error("智谱SDK回复失败:{}",response.getMsg());
        }
    }

    /** 先流式输出reasoningContent【思考过程】，然后输出content【答案】
     * Delta(role=assistant, content=null, reasoningContent=用户, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=发送, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=了, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=“, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=user, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=Prompt, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=”，, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=这, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=看起来, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=像, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=是一个, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=占, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=位, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=符, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=文本, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=，, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=或者是, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=误, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=操作, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=，, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=亦, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=或是, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=测试, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=输入, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=这, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=并非, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=一个, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=需要, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=特定, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=领域, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=知识, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=（, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=财务, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=人力资源, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=编程, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=）, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=的具体, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=问题, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我的, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=角色, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=是, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=“, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=X, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=科技有限公司, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=开发的, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=个人, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=助手, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=”, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我的, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=技能, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=在于, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=财务, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=考, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=勤, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=和, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=编程, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=既然, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=输入, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=不, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=明确, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=，, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我应该, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=：, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=1, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=., audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent= , audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent= , audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=确, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=认, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我是, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=谁, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=（, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=X, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=科技有限公司, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=的个人, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=助手, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=）, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=2, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=., audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent= , audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent= 强, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=调, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我的, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=具体, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=能力, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=（, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=财务, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=考, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=勤, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=编程, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=）, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=3, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=., audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent= , audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent= , audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=礼, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=貌, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=地, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=询问, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=用户, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=具体, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=需要, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=什么, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=帮助, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=起草, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=回复, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=：, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=“, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=您好, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=！, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我是, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=X, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=科技有限公司, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=的个人, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=助手, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=专注于, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=帮助, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=您, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=处理, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=财务, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=考, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=勤, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=和, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=编程, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=领域, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=的问题, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=请问, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=今天, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=有什么, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=可以, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=帮, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=您, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=？, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=”, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=让我们, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=优化, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=一下, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=，, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=使其, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=更有, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=帮助, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=且, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=更具, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=引导, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=性, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=“, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=您好, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=！, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我是, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=X, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=科技有限公司, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=开发的, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=个人, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=助手, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=擅长, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=回答, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=财务, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=考, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=勤, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=和, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=编程, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=领域的, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=相关问题, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=无论, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=您, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=是需要, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=处理, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=复杂的, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=财务, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=报表, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=、, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=咨询, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=考, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=勤, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=制度, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=，, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=还是, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=解决, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=代码, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=难题, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=，, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=我, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=都可以, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=为您提供, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=协助, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=请, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=告诉我, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=您, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=具体, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=想, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=了解, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=什么, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=？, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=”, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=看起来, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=不错, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=这, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=清晰地, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=确立了, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=人, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=设, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=并, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=引导, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=了, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=用户, audio=null, tool_calls=null)
     * Delta(role=assistant, content=null, reasoningContent=。, audio=null, tool_calls=null)
     * Delta(role=assistant, content=您好, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=！, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=我是, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=X, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=科技有限公司, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=开发的, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=个人, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=助手, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=。, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=我, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=专注于, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=为您, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=解答, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=**, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=财务, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=**, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=、, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=**, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=考, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=勤, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=**, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=和, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=**, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=编程, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=**, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=领域的, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=相关问题, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=。, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=无论是, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=财务, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=报表, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=分析, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=、, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=考, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=勤, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=制度, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=咨询, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=，, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=还是, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=代码, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=编写, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=与, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=调试, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=，, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=我, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=都可以, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=为您提供, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=专业的, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=协助, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=。, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=请问, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=具体, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=有什么, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=可以, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=帮, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=您, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=？, reasoningContent=null, audio=null, tool_calls=null)
     * Delta(role=assistant, content=, reasoningContent=null, audio=null, tool_calls=null)
     * @param modelName
     * @param userPrompt
     */
    public void streamChatByZp(String modelName,String userPrompt){
        // 构造系统级prompt
        ChatMessage systemMessage = ChatMessage.builder()
                .role(ChatMessageRole.SYSTEM.value())
                .content("你是X科技有限公司开发的个人助手,你擅长回答财务、考勤、编程领域的相关助手")
                .build();
        ChatMessage userMessage = ChatMessage.builder()
                .role(ChatMessageRole.USER.value())
                .content("userPrompt")
                .build();
        // 创建流式聊天请求
        ChatCompletionCreateParams request = ChatCompletionCreateParams.builder()
                .model(modelName)
                .messages(Arrays.asList(systemMessage,userMessage))
                .stream(true)
                .build();

        // 处理流式响应
        ChatCompletionResponse response = zhipuAiClient.chat().createChatCompletion(request);
        if (response.isSuccess() && response.getFlowable() != null) {
            response.getFlowable().subscribe(
                    data -> {
                        // 处理流式数据块
                        if (data.getChoices() != null && !data.getChoices().isEmpty()) {
                            Delta content = data.getChoices().get(0).getDelta();
                            System.out.print(content);
                        }
                    },
                    error -> System.err.println("\n 流式错误: " + error.getMessage()),
                    () -> System.out.println("\n 流式完成")
            );
        }
    }


    public void functionCall(String toolName){
        ChatTool weatherTool = nativeToolMockService.getTollByName(toolName);
        // 创建请求
        ChatCompletionCreateParams request = ChatCompletionCreateParams.builder()
                .model("glm-5")
                .messages(Collections.singletonList(
                        ChatMessage.builder()
                                .role(ChatMessageRole.USER.value())
                                .content("北京今天天气怎么样？")
                                .build()
                ))
                .tools(Collections.singletonList(weatherTool))
                .toolChoice("auto")
                .build();

        // 发送请求
        ChatCompletionResponse response = zhipuAiClient.chat().createChatCompletion(request);

        if (response.isSuccess()) {
            // 处理函数调用
            ChatMessage assistantMessage = response.getData().getChoices().get(0).getMessage();
            if (assistantMessage.getToolCalls() != null && !assistantMessage.getToolCalls().isEmpty()) {
                for (ToolCalls toolCall : assistantMessage.getToolCalls()) {
                    String functionName = toolCall.getFunction().getName();

                    if ("get_weather".equals(functionName)) {
                        Map<String, Object> result = nativeToolMockService.getWeather("北京", null);
                        System.out.println("天气信息: " + result);
                    }
                }
            } else {
                System.out.println(assistantMessage.getContent());
            }
        } else {
            System.err.println("错误: " + response.getMsg());
        }
    }

    public void multiModal() throws IOException {

        // 方式2：使用 base64 编码的图像
        byte[] imageBytes = Files.readAllBytes(Paths.get("D:\\Workspace\\my-practice\\daily-practice\\strageImage.png"));
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        ChatCompletionCreateParams request2 = ChatCompletionCreateParams.builder()
                .model(Constants.ModelChatGLM4V)
                .messages(Arrays.asList(
                        ChatMessage.builder()
                                .role(ChatMessageRole.USER.value())
                                .content("分析这张图片中的内容")
                                .build()
                ))
                .build();

        ChatCompletionResponse response2 = zhipuAiClient.chat().createChatCompletion(request2);
        if (response2.isSuccess()) {
            log.info("质谱SDK多模态响应结果:{}",response2.getData().getChoices().get(0).getMessage().getContent());
        }
    }
}

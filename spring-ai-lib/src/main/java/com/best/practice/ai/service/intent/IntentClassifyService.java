package com.best.practice.ai.service.intent;

import com.best.practice.ai.model.Intent.IntentResult;
import com.best.practice.ai.model.Intent.IntentValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

/**
 * 意图识别Service
 */
@Slf4j
@Service
public class IntentClassifyService {


    private final ChatClient chatClient;

    public IntentClassifyService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * 基于LLM的意图识别主业务流程
     */
    public IntentResult recognizeIntent(String message){
        //意图识别必须用 SystemPrompt 框定模型角色和规则，光靠 schema 不够稳。建议结构：
        String system = """
          你是一个XX科技有限公司开发的用于公司内部的意图识别助手。请根据用户输入，从下列预定义意图中选择一个最匹配的：
        
          - ENTERPRISE_KNOWLEDGE: 用户想查询企业知识,比如企业的规章制度，某个业务流程，IT系统的负责人等。可能会用到
          - PERSONAL_ENTER_OPT:   用户进行一些企业内部操作，比如查询我的OA代办，查询OA审批节点,帮用户催办流程等
          - PERSONAL_LOCAL_OPT:   用户想进行一些本地操作或者个人相关的操作，比如用户想转换md格式为word或者ppt，给我排一个工作计划等。
          - WORK_KNOWLEDGE:       用户想进行一些通用的世界知识的咨询，比如：编程领域、日常生活领域的知识等。
          - SMALL_TALK：          用户进行一些日常闲聊,比如：早上好，给我讲个笑话等
          - UNKNOWN:              无法归入以上任何类别
          
          规则：
          <HARD>
          1. 注意你是企业内部的意图识别助手,当用户咨询如何使用打印机、如何报销等知识，看似是通用的世界知识，其实是用户在企业内部办公的
          语境下咨询的，应该选择ENTERPRISE_KNOWLEDGE
          2. 必须严格从上述意图中选择，不要创造新意图。另外注意,用户只能进行个人的企业内部操作,比如用户只能查询自己的OA代办，不能查询其他人的代办。
          3. 槽位只填用户明确提到的，未提到的不要编造。
          4. confidence 反映你对判断的把握程度（0-1）。
          5. intent=SMALL_TALK 或 UNKNOWN 时，在 fallbackReply 里给出自然的中文回复；
          如果置信度低于0.3，在fallbackReply回复的内容中添加你需要的澄清；
          其他情况 fallbackReply 留空字符串。
          </HARD>
          
          示例：
          - 提问1：用户提问：这几天会下雨吗
          意图分析原因：
          ```
            用户提问这几天是否会下雨,这种不属于用户的个人操作反而属于世界知识WORK_KNOWLEDGE,
            当然模型训练完毕后不包括这几天的天气这种知识，需要调用webSearch等工具进行网络搜索。
            由于可能需要当前日期curDate，当前的位置curLocal等知识
          ```
          输出内容:{'intent':'WORK_KNOWLEDGE',slots:{'curDate':'2020-10-20','curLocal':'上海'},'confidence':0.8,}
          - 提问2：用户提问：早上好，给我讲个笑话,
          意图分析原因:
          ```
            这个明显和企业内部相关操作无关，也不需要世界知识，完全属于SMALL_TALK,需要给出自然的中文回复
          ```
          输出内容:{'intent':'SMALL_TALK','confidence':0.99,'fallbackReply':'早上好，早起的鸟儿有虫吃,早起的虫子被鸟吃'}
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

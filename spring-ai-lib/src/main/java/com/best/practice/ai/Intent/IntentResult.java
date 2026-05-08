package com.best.practice.ai.Intent;

import org.springframework.util.StringUtils;

import java.util.*;

public record IntentResult (
        Intent intent,                    // 主意图，枚举
        Map<String, String> slots,        // 槽位（参数）
        double confidence,                // 模型自评置信度 0~1
        String rawUtterance,              // 原始用户输入回填
        String fallbackReply              // intent=UNKNOWN 时的兜底回复
) {
    /**
     * 用 enum：BeanOutputConverter 会把 enum 的值列进 schema 的 enum 字段，模型只能从这几个选一个，避免天马行空
     */
    public enum Intent {
        QUERY_WEATHER,    // 查天气
        BOOK_FLIGHT,      // 订机票
        PLAY_MUSIC,       // 播放音乐
        SMALL_TALK,       // 闲聊（"晚上好"会落到这里）
        UNKNOWN           // 无法识别
    }

    /**
     * 构造一个兜底结果
     * @param rawUtterance
     * @param fallbackReply
     * @return
     */
    public static IntentResult unknown(String rawUtterance, String fallbackReply) {
        return new IntentResult(Intent.UNKNOWN, Map.of(), 0.0, rawUtterance, fallbackReply);
    }

    public static IntentValidation validate(IntentResult result){
        //写死各个意图需要的槽位
        final Map<Intent, Set<String>> REQUIRED_SLOTS = Map.of(
                Intent.QUERY_WEATHER, Set.of("city"),
                Intent.BOOK_FLIGHT,   Set.of("from", "to", "date"),
                Intent.PLAY_MUSIC,    Set.of()  // 无必填
        );
        Set<String> required = REQUIRED_SLOTS.getOrDefault(result.intent(), Set.of());
        Map<String, String> slots = result.slots() == null ? Map.of() : result.slots();

        List<String> missing = required.stream()
                .filter(k -> !StringUtils.hasText(slots.get(k)))
                .toList();
        //需要澄清
        if (!missing.isEmpty()) {
            return IntentValidation.needsClarification(result, missing);
        }
        return IntentValidation.ok(result);
    }
}
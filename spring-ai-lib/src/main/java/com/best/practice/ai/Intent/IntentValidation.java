package com.best.practice.ai.Intent;

import java.util.List;

public record IntentValidation (
        boolean validFlag,
        List<String> missingSlots,
        IntentResult intentResult
){
    public static IntentValidation needsClarification(IntentResult res,List<String> missingSlots){
        return new IntentValidation(false,missingSlots,res);
    }

    public static IntentValidation ok(IntentResult res){
        return new IntentValidation(false,List.of(),res);
    }
}

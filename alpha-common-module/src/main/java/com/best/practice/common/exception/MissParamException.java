package com.best.practice.common.exception;

import cn.hutool.core.util.StrUtil;

public class MissParamException extends RuntimeException{
    private static final String MISS_SUFFIX = "Missing Required key Param:";
    public MissParamException(String fieldName) {
        super(StrUtil.concat(true,MISS_SUFFIX,fieldName));
    }
}

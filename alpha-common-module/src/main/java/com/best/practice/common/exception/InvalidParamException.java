package com.best.practice.common.exception;

import cn.hutool.core.util.StrUtil;

import java.util.Objects;

public class InvalidParamException extends RuntimeException{

    private static final String MESSAGE_SUFFIX = "Invalid Parameter: ";
    private static final String NEED_TYPE_SUFFIX = "We need the param of type:";

    private static final String NEED_VALUE_RANGE_SUFFIX = "We need the param of value range:[";

    public InvalidParamException() {
        super();
    }

    /**
     * 报错信息,非法的参数
     * @param fieldName 字段/属性名
     */
    public InvalidParamException(String fieldName) {
        super(StrUtil.concat(true,MESSAGE_SUFFIX, fieldName));
    }

    /**
     * 报错信息非法类型的参数
     * @param fieldName 字段/属性名
     * @param type 取值范围,必须长度=2
     */
    public InvalidParamException(String fieldName, String type) {
        super(StrUtil.concat(true,MESSAGE_SUFFIX, fieldName, "; ",NEED_TYPE_SUFFIX, type));
    }

    /**
     * 报错信息非法范围的参数
     * @param fieldName 字段/属性名
     * @param valueRange 取值范围,必须长度=2
     */
    public InvalidParamException(String fieldName, String[] valueRange) {
        super(StrUtil.concat(true,MESSAGE_SUFFIX, fieldName,  "; ",NEED_VALUE_RANGE_SUFFIX,
                Objects.toString(valueRange[0],String.valueOf(Double.NEGATIVE_INFINITY)),
                "~",
                Objects.toString(valueRange[1],String.valueOf(Double.POSITIVE_INFINITY)),
                "]"));
    }
}

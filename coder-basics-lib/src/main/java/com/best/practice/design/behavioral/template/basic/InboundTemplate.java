package com.best.practice.design.behavioral.template.basic;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;

public abstract class InboundTemplate {
    public void doIntegrate(JSONObject thirdParam){
        //1. 参数校验，校验接口必填
        validateParams(thirdParam);
        //2. 解析参数
        JSONObject selfParams = parseParams(thirdParam);
        //3. 持久化数据
        JSONObject persistResult = persistData(selfParams);
        //4. 可选,根据持久化的结果做一些处理
        handleAfterPersist(persistResult);
    }

    /**
     * 解析三方系统传递的接口参数,主要校验是否必填
     * @param thirdParams
     */
    public abstract void validateParams(JSONObject thirdParams);

    /**
     * 三方系统参数转换
     * @param thirdParams 三方系统的传入方式
     * @return 本系统所需的JSONObject格式
     */
    public abstract JSONObject parseParams(JSONObject thirdParams);

    /**
     * 将解析后的数据持久化到目标系统
     * @param selfParams 本系统参数
     * @return 持久化结果
     */
    public abstract JSONObject persistData(JSONObject selfParams);


    /**
     * 持久化后做一些业务处理
     * @param selfParams
     */
    public void handleAfterPersist(JSONObject selfParams){

    }



}

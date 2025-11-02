package com.best.practice.design.behavioral.template.basic;

import cn.hutool.json.JSONObject;

public class OrderInboundService extends InboundTemplate{
    @Override
    public void validateParams(JSONObject thirdParams) {

    }

    @Override
    public JSONObject parseParams(JSONObject thirdParams) {
        return null;
    }

    @Override
    public JSONObject persistData(JSONObject selfParams) {
        return null;
    }
}

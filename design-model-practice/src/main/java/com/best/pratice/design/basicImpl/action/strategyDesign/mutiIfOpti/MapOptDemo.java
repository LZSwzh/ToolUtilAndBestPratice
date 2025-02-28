package com.best.pratice.design.basicImpl.action.strategyDesign.mutiIfOpti;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapOptDemo {
    public static void main(String[] args) {
        User user = new User();
        if (user == null) {
            throw new RuntimeException("user不能为空");
        }
        Map<String, BiConsumer<User, String>> validators = new HashMap<>();
        validators.put("name", MapOptDemo::validateName);
        validators.put("age", MapOptDemo::validateAge);
        validators.put("address", MapOptDemo::validateAddress);
        validators.put("phone", MapOptDemo::validatePhone);
        validators.put("sex", MapOptDemo::validateSex);

        validators.forEach((field, validator) -> validator.accept(user, "user的" + field + "不能为空"));
    }

    private static void validateName(User user, String message) {
        if (StringUtils.isEmpty(user.getName())) {
            addError(user, message);
        }
    }

    private static void validateAge(User user, String message) {
        if (StringUtils.isEmpty(user.getAge())) {
            addError(user, message);
        }
    }

    private static void validateAddress(User user, String message) {
        if (StringUtils.isEmpty(user.getAddress())) {
            addError(user, message);
        }
    }

    private static void validatePhone(User user, String message) {
        if (StringUtils.isEmpty(user.getPhone())) {
            addError(user, message);
        }
    }

    private static void validateSex(User user, String message) {
        if (StringUtils.isEmpty(user.getSex())) {
            addError(user, message);
        }
    }

    public static void addError(User user, String msg) {
        String oldErr = StringUtils.isEmpty(user.getErrMsg()) ? "" : user.getErrMsg();
        user.setErrMsg(oldErr + "," + msg);
    }

    @Data
    static class BaseBean {
        private String errMsg;
    }

    @Data
    static class User extends BaseBean {
        private String name;
        private String age;
        private String address;
        private String sex;
        private String phone;
    }
}
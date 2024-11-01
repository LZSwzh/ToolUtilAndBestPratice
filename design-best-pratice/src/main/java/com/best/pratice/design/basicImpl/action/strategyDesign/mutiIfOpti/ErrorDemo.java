package com.best.pratice.design.basicImpl.action.strategyDesign.mutiIfOpti;

import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ErrorDemo {
    public static void main(String[] args) {
        User user = new User();
        if (ObjectUtils.isEmpty(user)){
            throw new RuntimeException("user不能为空");
        }
        if (StringUtils.isEmpty(user.getName())){
            addError(user,"user的名字不能为空");
        }
        if (StringUtils.isEmpty(user.getAge())){
            addError(user,"user的年龄不能为空");
        }
        if (StringUtils.isEmpty(user.getAddress())){
            addError(user,"user的住址不能为空");
        }
        if (StringUtils.isEmpty(user.getPhone())){
            addError(user,"user的电话不能为空");
        }
        if (StringUtils.isEmpty(user.getSex())){
            addError(user,"user的性别不能为空");
        }
    }

    public static void addError(User user,String msg){
        String oldErr = StringUtils.isEmpty(user.getErrMsg()) ? "" : user.getErrMsg();
        user.setErrMsg(oldErr+","+msg);
    }

    @Data
    static class BaseBean{
        private String errMsg;
    }

    @Data
    static class User extends BaseBean{
        private String name;
        private String age;
        private String address;
        private String sex;
        private String phone;
    }


}

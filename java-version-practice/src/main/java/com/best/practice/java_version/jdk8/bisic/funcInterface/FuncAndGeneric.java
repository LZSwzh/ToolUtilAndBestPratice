package com.best.practice.java_version.jdk8.bisic.funcInterface;

import cn.hutool.core.lang.copier.Copier;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Supplier;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FuncAndGeneric {
    //写一个邮箱正则
    public static final String EMAIL_REGEX  = "^[a-zA-Z0-9_\\-.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,}$";
    public static void main(String[] args) throws Exception {
        String regex = "^[a-zA-Z0-9_\\-.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,}$";
        String email2 = "zhangsan@163.com";

        boolean isMatch = Pattern.matches(EMAIL_REGEX, email2);
        User user = new User("张三", 19, email2, "男");
        /** =======================  普通校验  ======================= */
        if (user == null) {
            throw new RuntimeException("用户不能为空");
        }
        validateName(user);
        validateAge(user);
        validateEmail(user);
        validateSex(user);
        /** =======================  函数式接口优化  ======================= */
        validateByFunc(user, User::getName, StrUtil::isEmpty, "用户名非法");
        validateByFunc(user, User::getAge, age->NumberUtil.compare(age,18)<=0, "用户名非法");
//        User nameErrUser = new User("", 19, "zhangsan@163.com", "男");
//        validateByFunc(nameErrUser, User::getName, StrUtil::isEmpty, "用户名非法");
//        User ageErrUser = new User("zhangsan", 14, "zhangsan@163.com", "男");
//        validateByFunc(ageErrUser, User::getAge, age->NumberUtil.compare(age,18)<=0, "年龄");
//        User emailErrUser = new User("zhangsan", 14, "asdhjfaious", "男");
//        validateByFunc(emailErrUser, User::getEmail, email->!EMAIL_REGEX.matches(email), "邮箱");
//        User sexErrUser = new User("zhangsan", 14, "asdhjfaious", "XXX");
//        validateByFunc(sexErrUser, User::getSex, sex->!"男".equals(sex) && !"女".equals(sex), "性别");
    }
    /** =======================  普通的校验方法  ======================= */
    /**
     * 普通的校验方法
     */
    public static void validateName(User user){
        if (user.getName() == null || "".equals(user.getName()))
            throw new RuntimeException("用户名非法");
    }

    public static void validateAge(User user){
        if (user.getAge()<=18)
            throw new RuntimeException("用户年龄非法");
    }
    public static void validateEmail(User user){
        if (user.getEmail() == null || "".equals(user.getEmail()))
            throw new RuntimeException("用户邮箱非法");
        if (!Pattern.matches(EMAIL_REGEX, user.getEmail()))
            throw new RuntimeException("用户邮箱格式非法");
    }
    public static void validateSex(User user){
        if (user.getSex() == null || "".equals(user.getSex()))
            throw new RuntimeException("用户性别非法");
        if (!("男".equals(user.getSex()) && !"女".equals(user.getSex())))
            throw new RuntimeException("用户性别非法");
    }

    /** =======================  函数式接口优化  ======================= */

//    public static void validateByFunc(User user){
//        Supplier<Integer> getAge = user::getAge;
//    }
    public static <T,R> void validateByFunc(T obj, Func1<T,R> getFunc, Predicate<R> validateFunc, String fieldName) throws Exception {
        //校验对象为空
        if (Objects.isNull( obj)) throw new RuntimeException("用户不能为空");
        //校验get方法返回null
        if (Objects.isNull(getFunc.call(obj))) throw new RuntimeException(fieldName+"字段不能为空");
        //校验get方法返回值
        if (validateFunc.test(getFunc.call(obj))) throw new RuntimeException(fieldName+"字段非法");
    }


    /** =======================  类  ======================= */

    static class User{
        private String name;
        private Integer age;
        private String email;
        private String sex;

        public User(String name, Integer age, String email, String sex) {
            this.name = name;
            this.age = age;
            this.email = email;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}

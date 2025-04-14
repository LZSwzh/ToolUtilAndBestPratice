package com.best.pratice.java_version.jdk11;

public class Jdk11Test {
    public static void main(String[] args) {
        /**
         * 新增特性1——字符串增强
         */
        System.out.println("字符串为空？ = " + "".isBlank());
        System.out.println("首尾去空格 = " + " asdfasd  sfa   ".strip());
        System.out.println("尾部去空格 = " + "javaStack  ".stripTrailing());
        System.out.println("首部去空格 = " + "   javaStack".stripLeading());
        System.out.println("复制字符串 = " + "Java".repeat(3));

        /**
         * 新增特性2——类型推断
         */
        var abc = "1111";

        System.out.println(abc instanceof String ? "true" : "false");

    }
}

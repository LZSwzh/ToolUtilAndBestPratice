package com.best.practice.java_version.jdk9.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tryTest {
    public static void main(String[] args) {
        /**
         * try-with-resource:
         *      JDK9前仅能在try-with-resource块中声明
         */
        try (Scanner sc=new Scanner(new File("D:\\workspace\\BestPractice\\ToolUtilAndBestPractice\\image2.jpeg"))){
            //这是JDK8之前的try-with-resource
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //JDK9允许放effective-final变量，即未被final修饰但值没有在声明后被修改
        Scanner sc = new Scanner("D:\\workspace\\BestPractice\\ToolUtilAndBestPractice\\image1.jpeg");
        Scanner sc2 = new Scanner("D:\\workspace\\BestPractice\\ToolUtilAndBestPractice\\image2.jpeg");
        try(sc;sc2) {

        }
    }
}

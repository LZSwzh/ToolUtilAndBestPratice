package com.best.pratice.java_version.jdk9;

import javax.activation.MimetypesFileTypeMap;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusDays(30);
        System.out.println(localDate.toString());
//        System.out.println();
//        uploadFile("截图.png");
//        System.out.println("-------------------------------------------------");
//        uploadFile("文档demo.doc");
//        System.out.println("-------------------------------------------------");
//        uploadFile("文档demo.txt");
//        System.out.println("-------------------------------------------------");
//        uploadFile("文档demo.docx");
//        System.out.println("-------------------------------------------------");
//        uploadFile("文档demo.jpg");
//        System.out.println("-------------------------------------------------");
//        uploadFile("文档demo.xlsx");
//        System.out.println("-------------------------------------------------");
//        uploadFile("文档demo.rar");
//        System.out.println("-------------------------------------------------");
//        uploadFile("文档demo.xls");

    }

    public static void uploadFile(String fileName) {

        // 使用MimetypesFileTypeMap获取文件的MIME类型
        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        final String contentType = mimeTypesMap.getContentType(fileName);

        System.out.println("文件名：" + fileName + " 类型：" + contentType);
    }
}

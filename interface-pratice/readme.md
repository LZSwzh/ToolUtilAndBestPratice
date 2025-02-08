
# Web Service

## WSDL代码生成
使用命令根据WSDL生成Java客户端代码
```text
wsimport -encoding utf-8 -d D:\test -keep -verbose http://127.0.0.1:8088/services/MeetingService?wsdl
```
-keep：这个选项告诉 wsimport 工具保留生成的 Java 源文件。
-verbose：这个选项使 wsimport 工具在执行过程中输出详细的信息


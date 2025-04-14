# 模块化
模块就是用来管理各个 package 的组件，它的概念，其实就可以理解为在 package 上面再包一层，\n  
包这一层的主要目的是让我们能够更好地组织和管理 Java 应用程序的代码.
JDK8的代码结构：
JDK8
├── bin
├── include
├── jre
│   ├── bin
│   └── lib
│       └── rt.jar
└── lib
    └── tools.jar
JDK9的代码结构:
JDK9
├── bin
├── conf
├── include
├── jmods
│   ├── java.base
│   └── java.logging
├── legal
└── lib
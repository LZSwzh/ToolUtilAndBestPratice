# 一、JDK8新特性

# 二、JDK9新特性
## 2.1.模块化
### 2.1.1.介绍
模块就是用来管理各个 package 的组件，它的概念，其实就可以理解为在 package 上面再包一层，\n  
包这一层的主要目的是让我们能够更好地组织和管理 Java 应用程序的代码.

在之前，文件依赖管理主要依赖JAR,当项目规模增大，依赖关系变得复杂容易出现
1. 依赖冲突：不同库可能依赖不同的jar版本，运行时报错
2. 类路径问题：类加载器从一个长路径搜索文件，可能报错文件找不到或重复加载问题
3. 依赖传递性问题：项目依赖的库依赖其他库，导致依赖非常复杂
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
### 2.1.2.模块的创建
1. 模块的创建
在【main/java】路径下，可以写一个module-info.java文件，注意函数名和位置有要求

```java
//JPMS是JDK9尝试引入的模块化特性
module com.best.practice.version.jdk9.jpms {
    /**
     * requires 表示当前模块依赖其他模块
     * 1、java.base 是JDK基础模块
     */
    requires java.base;
    /**
     * requires transitive 表示任何依赖当前模块的模块都需要引入desktop
     * 类似maven的依赖传递性
     */
    requires transitive java.desktop;
    /**
     * requires static 表示在编译期间依赖logging模块，运行期非必须
     */
    requires static java.logging;
    /**
     * exports:用于声明模块中可以被其他模块访问的包。要求包不能是空包【无java\properties等】
     */
    exports com.best.practice.version.jdk9.jpms;
    /**
     * exports...to...:限制包只对某些包可见
     */
    exports com.best.practice.version.jdk9.anonyclazz to com.best.practice.version.jdk9.inter;
    /**
     * opens:声明模块中可以被其他模块反射访问的包。
     */;
    opens com.best.practice.version.jdk9.inter;

    /**
     * provides:用于声明模块提供了服务接口的实现，但不指定服务提供者。
     */
//    provides com.example.service.ServiceInterface with com.example.service.ServiceImpl; // 提供服务实现
}
```
### 2.1.3.编译与运行
1.编译模块
```shell
# 编译
$ javac -d bin src/module-info.java src/com/best/practice/java_version/*.java
# 将bin下的文件打包成jar
$ jar --create --file=module.jar --main-class=com.best.practice.version.jdk9.jpms.JpmsApplication -C bin .
# 将打好的jar包转换成模块
$ jmod create --class-path module.jar module.jmod
```
2.运行模块
$ java --module-path module.jar --module hello.world
发现运行还是需要jar，那module干什么呢？
主要是为了打包JRE,过去需要先安装JRE,现在允许拆分JRE中需要的模块，从而减少JRE的体积。对方只需要运行:
$ jre/bin/java --module hello.world

## 接口增强
新增支持私有方法
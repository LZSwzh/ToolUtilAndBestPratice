module com.best.practice.java_version.jdk9.jpms {
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
    exports com.best.practice.java_version.jdk9.inter;
    /**
     * exports...to...:限制包只对某些包可见
     */
    exports com.best.practice.java_version.jdk9.anonyclazz to  com.best.practice.java_version.jdk9.inter;
    /**
     * opens:声明模块中可以被其他模块反射访问的包。
     */
     opens com.best.practice.java_version.jdk9.inter;

    /**
     * provides:用于声明模块提供了服务接口的实现，但不指定服务提供者。
     */
//    provides com.example.service.ServiceInterface with com.example.service.ServiceImpl; // 提供服务实现



}
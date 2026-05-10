package com.best.practice.version.jdk9.api;

/**
 * JDK9提供了进程API，允许对进程进行原生管理
 */
public class ProcessTest {
    public static void main(String[] args) {
        ProcessHandle currentProcess = ProcessHandle.current();
        // 输出进程ID
        System.out.println(currentProcess.pid());
        // 输出进程信息
        System.out.println(currentProcess.info());
        /*
        [
            user: Optional[?],#Optional可选的
            cmd: C: \Users\?\.jdks\azul-21.0.5\bin\java.exe,#JDK可执行文件
            startTime: Optional[2025-04-25T01: 33: 17.596Z],#进程启动时间
            totalTime: Optional[PT0.3125S]#进程运行总时间
        ]
         */
    }
}

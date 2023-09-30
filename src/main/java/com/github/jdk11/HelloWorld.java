package com.github.jdk11;

/**
 * ● 以前去执行 Java 程序，首要两个步骤：① 使用 javac 命令编译 xxx.java 源文件，② 使用 java 命令运行生成的 class 文件。
 * ● 但是，现在在 JDK 11 中，可以使用 java xxx.java 一步搞定，编译和运行。
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

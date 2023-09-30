package com.github.jdk11;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class JDK11Test {
    /**
     *  在 JDK 10 的时候，引入了 var 来声明局部变量，但是不可以在 var 上添加注解
     *  而 JDK11 是可以的，其目的就是：在使用 Lambda 表达式给参数加上注解。
     */
    @Test
    public void test() {
        // JDK 10 之前，必须要有类型，但是，在 JDK 的时候，var 不能使用在方法形参上
        Consumer<String> con = (@Deprecated String t) -> System.out.println("t.toLowerCase() = " + t.toLowerCase());
        // JDK 11 之后，可以使用 var ，当在使用 Lambda 表达式的时候给形参加上注解
        Consumer<String> con2 = (@Deprecated var t) -> System.out.println("t.toLowerCase() = " + t.toLowerCase());
    }
}

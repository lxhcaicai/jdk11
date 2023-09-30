package com.github.jdk11;

import org.junit.jupiter.api.Test;

public class StringTest {
    /**
     *  判断字符串是否空白
     */
    @Test
    public void test() {
        String str = "";

        System.out.println(str.isBlank()); // true
        System.out.println(str.isEmpty()); // true

        str = "       ";
        System.out.println(str.isBlank()); // true
        System.out.println(str.isEmpty()); // false

        str = "abc";
        System.out.println(str.isBlank()); //false
        System.out.println(str.isEmpty()); //false

        str = " \r \t ";
        System.out.println(str.isBlank()); //true
        System.out.println(str.isEmpty()); //false
    }
}

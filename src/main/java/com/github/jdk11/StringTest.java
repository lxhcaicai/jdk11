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

    /**
     * 去除收尾空白
     *
     * 去除收尾空白：包括全角空白字符。
     * public String strip() {}
     *
     * 去除收尾空白：不包括全角空白字符。
     * public String trim() {}
     *
     */
    @Test
    public void test2() {
        String str = "　abc　"; // 有全角空白
        System.out.println("str.trim() =" + str.trim()); //  str.trim() =　abc
        System.out.println("str.strip() =" + str.strip()); // str.strip() =abc
    }

    /**
     * 去除尾部空白或首部空白
     *
     * 去除尾部空白：
     * public String stripTrailing() {}
     *
     * 去除首部空白
     * public String stripLeading() {}
     */
    @Test
    public void test3() {
        System.out.println("----" + "  JavaScript   ".stripLeading() + "----"); // ----JavaScript  ----
        System.out.println("----" + "  JavaScript   ".stripTrailing() + "----"); // ----  JavaScript----

    }

    /**
     * 复制字符串
     * public String repeat(int count) {}
     *
     */
    @Test
    public void test4() {
        System.out.println("java".repeat(3)); // javajavajava
    }

    /**
     * 行数统计：
     * public Stream<String> lines() {}
     */
    @Test
    public void test5() {
        System.out.println("你好啊~\n我很好~\n你呢？\n当然，不错。\n幸好...".lines().count()); // 5
    }
}

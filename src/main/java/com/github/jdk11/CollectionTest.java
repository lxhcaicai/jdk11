package com.github.jdk11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CollectionTest {

    /**
     *  以前，集合转换数组需要创建一个数组对象：
     *  <T> T[] toArray(T[] a);
     *
     *  现在，集合转换数组支持方法引用
     *  default <T> T[] toArray(IntFunction<T[]> generator) {
     *     return toArray(generator.apply(0));
     * }
     *
     */
    @Test
    public void test() {
        List<Integer> list = List.of(1,2,3,4,5,6);

        // JDK 11 之前
        Integer []array = list.toArray(new Integer[list.size()]);
        System.out.println("array = " + Arrays.toString(array));

        // JDK 11 之后
        Integer []array2 = list.toArray(Integer[]::new);
        System.out.println("array = " + Arrays.toString(array2));
    }
}

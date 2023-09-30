package com.github.jdk11;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class OptionTest {

    /**
     * JDK 8
     * 创建 Optional 对象：创建一个空的（没有元素的）Optional
     * public static<T> Optional<T> empty(){}
     *
     * 创建 Optional 对象：创建一个非空的（有元素的）Optional
     * public static <T> Optional<T> of(T value){}
     *
     * 创建 Optional 对象：创建一个可能为空的（没有元素的），也可能是非空的（有元素的）Optional
     * public static <T> Optional<T> ofNullable(T value){}
     *
     */
    @Test
    public void test() {
        // 创建空的 Optional 对象
        Optional<String> empty = Optional.empty();
        System.out.println("empty = " + empty); // empty = Optional.empty

        // 创建一个非空的 Optional 对象，一旦传递了 null，就会抛出异常
        Optional<Integer> optionalInteger = Optional.of(1);
        System.out.println("optional = " + optionalInteger); // optional = Optional[1]

        // 创建一个可能为空的，也可能是非空的 Optional 对象
        Optional<String> optional2 = Optional.ofNullable(null);
        System.out.println("option2 = " + optional2); // optional2 = Optional.empty
        Optional<String> optional3 = Optional.ofNullable("abc");
        System.out.println("option3 = " + optional3); // option3 = Optional[abc]
    }

    /**
     * JDK8
     * 从 Optional 容器中获取元素：获取元素，要求Optional容器必须非空
     * public T get() {}
     *
     * Optional 的判断方法：判断 Optional 容器中是否有元素
     * public boolean isPresent() {}
     */
    @Test
    public void test2() {
        Optional<Integer> optional = Optional.ofNullable(1);
        // 如果 Optional 对象中元素不为 null
        if (optional.isPresent()) {
            // 就取出 Optional 对象中的元素
            Integer intValue = optional.get();
            System.out.println("intValue = " + intValue);
        }
    }

    /**
     * JDK8
     * 从 Optional 容器中获取元素：如果容器中有元素，就返回容器中的元素；否则，就返回 Supplier 接口的 Lambda 表达式提供的值
     * public T orElseGet(Supplier<? extends T> supplier){}
     */
    @Test
    public void test3() {
        String str = "abc";
        // Optional 容器中有元素
        Optional<String> optional = Optional.ofNullable(str);
        String s = optional.orElseGet(String::new);
        System.out.println("s = " + s);

        // Optional 容器中没有元素
        str = null;
        optional = Optional.ofNullable(str);
        s = optional.orElseGet(String::new);
        System.out.println("s=" + s);
    }

    /**
     *从 Optional 容器中获取元素：如果容器中有元素，就返回容器中的元素；否则，就抛出指定的异常
     * public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X{}
     */
    @Test
    public void test4() {
        String str = "abc";
        // Optional 容器中有元素
        Optional<String> optional = Optional.ofNullable(str);
        String s = optional.orElseThrow(() -> new RuntimeException("Optional 容器中没有元素"));
        System.out.println("s = " + s);

        str = null;
        // Optional 容器中没有元素
        optional = Optional.ofNullable(str);
        s = optional.orElseThrow(() -> new RuntimeException("Optional 容器中没有元素"));
        System.out.println("s = " + s);
    }

    /**
     * Optional 的判断方法：判断 Optional 容器中是否有元素
     * public boolean isPresent() {}
     */
    @Test
    public void test5() {
        Optional<Integer> optional = Optional.ofNullable(1);
        // 如果 Optional 对象中元素不为 null
        if (optional.isPresent()) {
            // 就取出 Optional 对象中的元素
            Integer intValue = optional.get();
            System.out.println("intValue = " + intValue);
        } else {
            System.out.println("optional 容器中没有元素");
        }
    }

    /**
     * Optional 的判断方法：判断 Optional 容器中是否有元素，如果有，则对它进行 Consumer 指定的操作；否则，啥也不做
     * public void ifPresent(Consumer<? super T> action){}
     */
    @Test
    public void test6() {
        String str = "abc";
        // Optional 容器中有元素
        Optional<String> optional = Optional.ofNullable(str);
        optional.ifPresent(System.out::println); //abc

        // Optional 容器中没有元素
        str = null;
        optional = Optional.ofNullable(str);
        optional.ifPresent(System.out::println);
    }

    /**
     *Optional 的判断方法：判断 Optional 容器中是否有元素，如果有，则对它进行 Function 接口指定的操作；否则，啥也不做
     *public <U> Optional<U> map(Function<? super T, ? extends U> mapper){}
     * public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper) {}
     */
    @Test
    public void test7() {
        String str = "abc";
        // Optional 容器中有元素
        Optional<String> optional = Optional.ofNullable(str);
        optional = optional.map(String::toUpperCase);
        System.out.println("optional = " + optional); // optional = Optional[ABC]

        // Optional 容器中没有元素
        str = null;
        optional = Optional.ofNullable(str);
        optional = optional.map(String::toUpperCase);
        System.out.println("optional = " + optional); // optional = Optional.empty
    }

    /**
     * Optional 的过滤方法：根据指定条件，过滤 Optional 中的元素
     * public Optional<T> filter(Predicate<? super T> predicate){}
     */
    @Test
    public void test8() {
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9);
        Optional<List<Integer>> optional = Optional.ofNullable(list).filter(x -> x.contains(5));
        optional.ifPresent(System.out::println);
    }

}

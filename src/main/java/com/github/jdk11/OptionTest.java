package com.github.jdk11;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    /**
     * JDK9 中的 Optional 的方法
     *
     * 如果 Optional 中的元素不为空，就执行参数 1 的功能；否则，执行参数 2 的功能
     * public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction){}
     *
     */
    @Test
    public void test9() {
        String str = "abc";
        // Optional 容器中有元素
        Optional<String> optional = Optional.ofNullable(str);
        optional.ifPresentOrElse(System.out::println,()-> System.out.println("当前 Optional 中没有元素")); // abc

        // Optional 容器中没有元素
        str = null;
        optional = Optional.ofNullable(str);
        optional.ifPresentOrElse(System.out::println,()-> System.out.println("当前 Optional 中没有元素")); // 当前 Optional 中没有元素
    }

    /**
     * JDK9
     * 如果 Optional 中的元素不为空，则返回对应的 Optional ；否则，返回形参封装的 Optional ：
     * public Optional<T> or(Supplier<? extends Optional<? extends T>> supplier) {}
     */
    @Test
    public void test10() {
        String str = "abc";
        // Optional 容器中有元素
        Optional<String> optional = Optional.ofNullable(str);
        optional = optional.or(() -> Optional.of("当前 Optional 没有元素"));
        optional.ifPresent(System.out::println); // abc

        str = null;
        optional = Optional.ofNullable(str);
        optional = optional.or(() -> Optional.of("当前 Optional 没有元素"));
        optional.ifPresent(System.out::println); // 当前 Optional 没有元素
    }


    /**
     * JDK9
     * 如果 Optional 中的元素不为空，则返回包含此元素的 Stream；否则，返回一个空的 Stream
     * public Stream<T> stream(){}
     */
    @Test
    public void test11() {
        String str = "abc";
        Optional<String> optional = Optional.ofNullable(str);
        Stream<String> stream = optional.stream();
        System.out.println("stream.count() = " + stream.count());

        str = null;
        optional = Optional.ofNullable(str);
        stream = optional.stream();
        System.out.println("stream.count() = " + stream.count());
    }

    /**
     * JDK10
     * 如果 Optional 容器中有元素，则返回该元素；否则，将抛出 NoSuchElementException 异常。
     * public T orElseThrow() {}
     */
    @Test
    public void test12() {
        String str = "abc";
        Optional<String> optional = Optional.ofNullable(str);
        String s = optional.orElseThrow();
        System.out.println("s = " + s); // s = abc

        str = null;
        optional = Optional.ofNullable(str);
        s = optional.orElseThrow(); // java.util.NoSuchElementException: No value present
        System.out.println("s = " + s);
    }

    /**
     * JDK11
     * 判断 Optional 容器中是否有元素，如果没有元素，返回 true；否则，返回 false，可以类比 JDK8 中的 isPresent() 方法。
     * public boolean isEmpty() { }
     */
    @Test
    public void test13() {
        String str = "abc";
        Optional<String> optional = Optional.ofNullable(str);
        boolean empty = optional.isEmpty();
        System.out.println("empty = " + empty); // empty = false

        str = null;
        optional = Optional.ofNullable(str);
        empty = optional.isEmpty();
        System.out.println("empty = " + empty); // empty = true
    }
}

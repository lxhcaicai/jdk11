package com.github.jdk11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
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

    /**
     * 同步
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws IOException, InterruptedException {
        // 创建 HttpClient 对象
        HttpClient httpClient = HttpClient.newHttpClient();
        // 创建 HttpRequest 请求对象
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).GET().build();
        // 使用 HttpClient 对象发起 request 请求，得到请求响应对象 response
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        // 响应状态码
        System.out.println("响应状态码:" + response.statusCode());
        // 响应信息
        System.out.println("响应消息:" + response.body());
    }

    /**
     * 异步
     */
    @Test
    public void test3() {
        // 创建 HttpClient 对象
        HttpClient httpClient = HttpClient.newHttpClient();
        // 创建 HttpRequest 请求对象
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).GET().build();
        // 发送请求
        CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        // 异步监听结果数据
        future.whenComplete((response, throwable)->{
            // 结果数据的处理
            if (null  != throwable) {
                throwable.printStackTrace();
            } else {
                // 响应状态码
                System.out.println("响应状态码:" + response.statusCode());
                // 响应信息
                System.out.println("响应消息:" + response.body());
            }
        }).join(); // 阻塞等待异步结果

        System.out.println("结束程序");
    }
}

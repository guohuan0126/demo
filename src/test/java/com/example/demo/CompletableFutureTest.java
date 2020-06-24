package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2020-06-17
 **/
@SpringBootTest
public class CompletableFutureTest {

    ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Test
    public void CompletableFutureTest(){
        long start = System.currentTimeMillis();
        System.out.println("开始="+start);
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(()->{
            return task1("task1","task1=", "task1 结束");
        },executorService);

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(()->{
            return task1("task2","task2=", "task2 结束");
        },executorService);

/*        CompletableFuture<String> task3 = task2.thenCombine(task1, (s1, s2) -> {
            for (int i = 0 ;i<1000000;i++){
                System.out.println(s1+"-"+s2+"="+i);
            }
            System.out.println("task3结束=:" + s1 + s2);
            return s1 + s2;
        });*/
        CompletableFuture<String> task3 = task2.thenApplyAsync((s -> {
            return task1("task3", "task3=", "task3 结束");
        }),executorService);

        CompletableFuture.allOf(task1,task2,task3).join();

        System.out.println("all task finish");
        System.out.println("结束="+(System.currentTimeMillis()-start));

    }


    @Test
    public void CompletableFutureTest1(){

        long start = System.currentTimeMillis();
        System.out.println("开始="+start);

        String s1 = task1("task1", "task1=", "task1 结束");


        String s2 = task1("task2", "task2=", "task2 结束");


        String s3 = task1("task3", "task3=", "task3 结束");

        System.out.println("task3结束=:" + s1 + s2);

        System.out.println("all task finish");

        System.out.println("结束="+(System.currentTimeMillis()-start));
    }

    private String task1(String s, String s1, String s2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(s1 + i+Thread.currentThread().getName());
        }
        System.out.println(s2);
        return s;
    }

    @Test
    public void test(){
        BigDecimal bigDecimal =
                BigDecimal.valueOf(3).subtract(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(5)).setScale(0,
                BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);

    }

    @Test
    public void test1(){
        Integer a = -2;
        Integer b = -1;
        int i = a.compareTo(b);
        System.out.println(i);

    }
}

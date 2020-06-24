package com.example.demo;

import com.example.demo.service.impl.IdLeafForDbImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {


    @Resource
    IdLeafForDbImpl idLeafForDb;

    @Test
    void contextLoads() {
    }

    @Test
    void DBTest() {
    }

    private ExecutorService executorService = new ThreadPoolExecutor(10, 100, 3000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());

    @Test
    public void threadTest() {
        int count = 0;
        while (count < 10000) {
            count++;
            System.out.println("第"+count+"次");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Thread t = Thread.currentThread();
                    Long id = idLeafForDb.getId();
                    System.out.println("threadName=" + t.getName() + "id:" + id);

                }
            };
            executorService.submit(runnable);
        }
    }
}

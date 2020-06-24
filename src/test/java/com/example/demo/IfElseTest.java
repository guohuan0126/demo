package com.example.demo;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2020-06-18
 **/
@SpringBootTest
public class IfElseTest {

    @Test
    public void test(){

        int s = 1;
        if (s>0) {
            s = doAction1(s);
        } else if (s<0) {
            s = doAction2(s);
        } else if (s==0) {
            s = doAction3(s);
        }
        System.out.println(s);
    }

    public int doAction3(int s) {
        return 0;
    }

    public int doAction2(int s) {
        return -s;
    }

    public int doAction1(int s) {
        return s;
    }

    @Test
    public void test1(){

    }

}

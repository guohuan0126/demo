package com.example.demo.service.impl;

import com.example.demo.service.interf.Operation;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2020-06-24
 **/
public class Division implements Operation {
    @Override
    public int apply(int a, int b) {
        return a-b;
    }
}

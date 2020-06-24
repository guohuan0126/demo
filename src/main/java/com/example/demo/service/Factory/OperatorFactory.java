package com.example.demo.service.Factory;

import com.example.demo.service.impl.Addition;
import com.example.demo.service.impl.Division;
import com.example.demo.service.interf.Operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2020-06-24
 **/
public class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>(8);
    static {
        operationMap.put("add", new Addition());
        operationMap.put("div", new Division());
    }

    public static Optional<Operation> getOperation(String operation){
        return Optional.ofNullable(operationMap.get(operation));
    }
}

package com.example.demo.service.impl;

import com.example.demo.mapper.IdRecordMapper;
import com.example.demo.service.interf.IdService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2019-11-01
 **/
@Service
public class BaseServiceImpl implements InitializingBean {

    @Resource
    protected IdRecordMapper idRecordMapper;

    @Resource
    protected IdService idService;

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

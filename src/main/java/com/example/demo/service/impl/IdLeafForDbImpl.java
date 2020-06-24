package com.example.demo.service.impl;

import com.example.demo.entity.IdRecord;
import com.example.demo.model.IdSegment;
import com.example.demo.service.interf.IdService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2019-11-04
 **/
@Service
@Slf4j
@Data
public class IdLeafForDbImpl implements InitializingBean {

    @Autowired
    private IdService idService;
    private ExecutorService executorService;

    public IdLeafForDbImpl() {
    }

    /**
     * 这两段用来存储每次拉升之后的最大值
     */
    private volatile IdSegment[] segment = new IdSegment[2];
    private volatile boolean sw;
    private AtomicLong currentId;
    /**
     *     功能性严重bug #5 一个实例一把锁
     */
    private ReentrantLock lock = new ReentrantLock();
    private volatile FutureTask<Boolean> asynLoadSegmentTask = null;
    private String bizTag = "order";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public void init(){

    }

    public Long getId() {
        if (segment[index()].getMiddleId() <= currentId.longValue()
                && isNotLoadOfNextsegment()
                && asynLoadSegmentTask == null){

            try {
                lock.lock();
                if (segment[index()].getMiddleId() <= currentId.longValue()){
                    asynLoadSegmentTask = new FutureTask<>(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            final int currentIndex = reIndex();
                            segment[currentIndex] = doUpdateSegment(bizTag);
                            return true;
                        }
                    });
                    executorService.submit(asynLoadSegmentTask);
                    log.info("init asynLoadSegmentTask...，taskExecutor={}", executorService.toString());
                }
            } finally {
                lock.unlock();
            }

        }
        if (segment[index()].getMaxId() <= currentId.longValue()){
            try {
                lock.lock();
                if (segment[index()].getMaxId() <= currentId.longValue()) {
                    boolean loadingResult = false;

                    try {
                        loadingResult = asynLoadSegmentTask.get(500, TimeUnit.MINUTES);
                        if (loadingResult) {
                            setSw(!isSw());
                            currentId = new AtomicLong(segment[index()].getMinId());
                            asynLoadSegmentTask = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        loadingResult = false;
                        asynLoadSegmentTask = null;
                        log.info("更新切换失败");
                    }
                    if (!loadingResult) {
                        while (isNotLoadOfNextsegment()) {
                            final int currentIndex = reIndex();
                            segment[currentIndex] = doUpdateSegment(bizTag);
                            setSw(!isSw());
                            currentId = new AtomicLong(segment[index()].getMinId());
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return currentId.incrementAndGet();
    }

    private int index() {
        if (isSw()) {
            return 1;
        } else {
            return 0;
        }
    }

    private int reIndex() {
        if (isSw()) {
            return 0;
        } else {
            return 1;
        }
    }
    private boolean isNotLoadOfNextsegment() {
        if (segment[reIndex()] == null) {
            return true;
        }
        return segment[reIndex()].getMinId() < segment[index()].getMinId();
    }

    private IdSegment doUpdateSegment(String bizTag){
        final IdSegment idSegment;
        try {
            lock.lock();
            IdRecord idRecord = idService.getByBizTag(bizTag);
            idSegment = new IdSegment();
            Long newMaxId = idRecord.getMaxId()+idRecord.getStep();
            idSegment.setMaxId(newMaxId);
            idSegment.setStep(idRecord.getStep());
            idService.updateMaxId(newMaxId, idRecord);
        } finally {
            lock.unlock();
        }
        return idSegment;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        if (bizTag==null){
            throw new RuntimeException("bizTag must be not null");
        }
        if (executorService==null){
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("id ").build();
            executorService = new ThreadPoolExecutor(2,2,3000,TimeUnit.SECONDS
                    ,new LinkedBlockingQueue<Runnable>(),threadFactory);
        }
        segment[0] = doUpdateSegment(bizTag);
        setSw(false);
        currentId = new AtomicLong(segment[index()].getMinId());
    }
}

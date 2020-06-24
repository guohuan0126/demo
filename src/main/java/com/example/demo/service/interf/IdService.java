package com.example.demo.service.interf;

import com.example.demo.entity.IdRecord;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2019-11-01
 **/
public interface IdService {
    /**
     * get idRecord by bizTag
     * @param bizTag
     * @return
     */
    IdRecord getByBizTag(String bizTag);
    /**
     * update idRecord maxId by bizTag
     * @param
     * @return
     */
    void updateMaxId(Long newMaxId, IdRecord idRecord);
}

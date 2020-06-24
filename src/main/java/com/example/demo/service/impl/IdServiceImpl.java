package com.example.demo.service.impl;

import com.example.demo.common.exception.AppException;
import com.example.demo.common.response.CommonResponseCode;
import com.example.demo.entity.IdRecord;
import com.example.demo.entity.IdRecordExample;
import com.example.demo.service.interf.IdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2019-11-01
 **/
@Service
public class IdServiceImpl extends BaseServiceImpl implements IdService {

    @Override
    public IdRecord getByBizTag(String bizTag) {
        IdRecordExample idRecordExample = new IdRecordExample();
        idRecordExample.or().andBizTagEqualTo(bizTag);
        List<IdRecord> idRecords = idRecordMapper.selectByExample(idRecordExample);
        if (idRecords.isEmpty()){
            throw new AppException(CommonResponseCode.INNER_ERROR);
        }
        return idRecords.get(0);
    }

    @Override
    public void updateMaxId(Long newMaxId, IdRecord idRecord) {

        IdRecord newRecord = new IdRecord();
        newRecord.setMaxId(newMaxId);
        IdRecordExample example = new IdRecordExample();
        example.or().andBizTagEqualTo(idRecord.getBizTag()).andMaxIdEqualTo(idRecord.getMaxId());
        idRecordMapper.updateByExampleSelective(newRecord, example);
    }

}

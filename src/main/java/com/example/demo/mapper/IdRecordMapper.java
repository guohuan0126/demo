package com.example.demo.mapper;

import com.example.demo.entity.IdRecord;
import com.example.demo.entity.IdRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface IdRecordMapper {
    long countByExample(IdRecordExample example);

    int deleteByExample(IdRecordExample example);

    int deleteByPrimaryKey(String bizTag);

    int insert(IdRecord record);

    int insertSelective(IdRecord record);

    List<IdRecord> selectByExampleWithRowbounds(IdRecordExample example, RowBounds rowBounds);

    List<IdRecord> selectByExample(IdRecordExample example);

    IdRecord selectByPrimaryKey(String bizTag);

    int updateByExampleSelective(@Param("record") IdRecord record, @Param("example") IdRecordExample example);

    int updateByExample(@Param("record") IdRecord record, @Param("example") IdRecordExample example);

    int updateByPrimaryKeySelective(IdRecord record);

    int updateByPrimaryKey(IdRecord record);
}
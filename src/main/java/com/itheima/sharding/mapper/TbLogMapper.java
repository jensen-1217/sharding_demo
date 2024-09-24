package com.itheima.sharding.mapper;

import com.itheima.sharding.entity.TbLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.itheima.sharding.entity.TbLog
 */
@Mapper
public interface TbLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TbLog record);

    int insertSelective(TbLog record);

    TbLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbLog record);

    int updateByPrimaryKey(TbLog record);

}





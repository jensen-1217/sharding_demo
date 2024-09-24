package com.itheima.sharding.mapper;

import com.itheima.sharding.entity.TDict;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.itheima.sharding.entity.TDict
 */
@Mapper
public interface TDictMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TDict record);

    int insertSelective(TDict record);

    TDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TDict record);

    int updateByPrimaryKey(TDict record);

}





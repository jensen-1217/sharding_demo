package com.itheima.sharding.mapper;

import com.itheima.sharding.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.itheima.sharding.entity.TUser
 */
@Mapper
public interface TUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

}





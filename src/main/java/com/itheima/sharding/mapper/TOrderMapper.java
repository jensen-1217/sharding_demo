package com.itheima.sharding.mapper;

import com.itheima.sharding.entity.TOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.itheima.sharding.entity.TOrder
 */
@Mapper
public interface TOrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    /**
     * 根据用户id精准查询，获取订单集合
     * @param userId
     * @return
     */
    List<TOrder> findByUserId(Long userId);

    /**
     * 根据用户id范围查询对应的订单集合信息
     * @param start
     * @param end
     * @return
     */
    List<TOrder> findRangeByUserId(@Param("start") Long start,@Param("end") Long end);

    /**
     * 根据订单id范围查询
     * @param start
     * @param end
     * @return
     */
    List<TOrder> selectByRange(@Param("start") Long start,@Param("end") Long end);

    /**
     * 根据用户id等值查询，并且根据订单id范围查询
     * @param userId
     * @param start
     * @param end
     * @return
     */
    List<TOrder> selectByRange2(@Param("userId") Long userId,@Param("start") Long start,@Param("end") Long end);
}





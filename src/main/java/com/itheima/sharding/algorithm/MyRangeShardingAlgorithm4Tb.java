package com.itheima.sharding.algorithm;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author by itheima
 * @Date 2022/6/11
 * @Description 定义范围查询时匹配表的算法
 *  对应精准查询，只匹配一个库 --》string
 *  对应范围查询来说，可能需要匹配多个库，才能获取数据--》Collection<String>
 *   保证片键使用between and
 */
public class MyRangeShardingAlgorithm4Tb implements RangeShardingAlgorithm<Long> {

    /**
     *
     * @param tbNames 数据源集合
     * @param shardingValue 范围查询信息封装
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> tbNames, RangeShardingValue<Long> shardingValue) {
        //获取分片字段
        String columnName = shardingValue.getColumnName();
        //获取逻辑表
        String logicTableName = shardingValue.getLogicTableName();
        //获取范围数据
        Range<Long> valueRange = shardingValue.getValueRange();
        //select * from t_order where user_id between 1 and 20;
        //判断是否有上限值
        if (valueRange.hasUpperBound()) {
            //获取上限值 20
            Long uppper = valueRange.upperEndpoint();
            System.out.println(uppper);
        }
        if (valueRange.hasLowerBound()){
            Long lower = valueRange.lowerEndpoint();
            System.out.println(lower);
        }
        //理论上要根据上限值和下限值获取满足条件的数据源集合
        return Arrays.asList("t_order_1","t_order_2");
    }
}

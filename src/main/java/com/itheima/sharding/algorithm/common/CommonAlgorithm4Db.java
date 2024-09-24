package com.itheima.sharding.algorithm.common;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author by itheima
 * @Date 2022/6/11
 * @Description 定义精准查询数据库的算法类 接口中的泛型与数据库的片键类型一致
 * 保证片键中使用= in
 */
public class CommonAlgorithm4Db implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

    /**
     * 定义匹配数据源的方法
     * @param dsNames 所有配置的数据源的集合ds${1..2} 包含ds1 ds2封装到该集合下
     *                说白了就是sharinding把所有的数据源都给你，然后让你根据片键值选择
     * @param shardingValue 封装分片相关信息
     * @return 返回匹配的数据源
     */
    @Override
    public String doSharding(Collection<String> dsNames, PreciseShardingValue<Long> shardingValue) {
        //获取数据库分片的字段名称 user_id = in
        String columnName = shardingValue.getColumnName();
        //获取逻辑表名称
        String logicTableName = shardingValue.getLogicTableName();
        //获取片键对应的值 select * from t_order where user_id=10,这里的value就等于10
        Long value = shardingValue.getValue();
        //一般是根据片键值获取对应的数据源，并返回
        String sufix=String.valueOf(value % 2 +1);
        String dsName = dsNames.stream().filter(ds -> ds.endsWith(sufix)).findFirst().get();
        return dsName;
    }

    /**
     *
     * @param dsNames 数据源集合
     * @param shardingValue 范围查询信息封装
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> dsNames, RangeShardingValue<Long> shardingValue) {
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
        return Arrays.asList("ds1","ds2");
    }
}

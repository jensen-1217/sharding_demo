package com.itheima.sharding;

import com.itheima.sharding.entity.TDict;
import com.itheima.sharding.entity.TOrder;
import com.itheima.sharding.entity.TUser;
import com.itheima.sharding.entity.TbLog;
import com.itheima.sharding.mapper.TDictMapper;
import com.itheima.sharding.mapper.TOrderMapper;
import com.itheima.sharding.mapper.TUserMapper;
import com.itheima.sharding.mapper.TbLogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class ShardingDemoApplicationTests {

    @Autowired
    private TOrderMapper tOrderMapper;

    /**
     * 测试水平分表功能
     */
    @Test
    public void test1() {
        int orderId=0;
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            //保证随机生成奇数或者偶数
            orderId+=random.nextInt(2)+1;
            TOrder order = TOrder.builder().orderId(Long.valueOf(orderId))
                    .userId(Long.valueOf(i))
                    .status("1")
                    .price(new BigDecimal(300))
                    .build();
            tOrderMapper.insert(order);
        }
    }

    /**
     * @Description 测试分库分表功能
     */
    @Test
    public void test2(){
        int orderId=0;
        int userId=0;
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            //保证随机生成奇数或者偶数
            orderId+=random.nextInt(2)+1;
            userId+=random.nextInt(2)+1;
            TOrder order = TOrder.builder().orderId(Long.valueOf(orderId))
                    .userId(Long.valueOf(userId))
                    .status("1")
                    .price(new BigDecimal(300))
                    .build();
            tOrderMapper.insert(order);
        }
    }

    @Autowired
    private TDictMapper tDictMapper;

    /**
     * 测试广播表：
     *      当对法广播表进行增删改操作时，操作的sql会广播给各个数据源
     */
    @Test
    public void commonTable(){
        TDict build = TDict.builder().dictId(1l).code("666").type("1").value("888")
                .build();
        tDictMapper.insert(build);
    }

    @Autowired
    private TUserMapper tUserMapper;

    @Test
    public void test04(){
        TUser user = TUser.builder().userId(133l).userType("1")
                .fullname("laozhang").build();
        tUserMapper.insert(user);
    }


    @Autowired
    private TbLogMapper tbLogMapper;
    /**
     * 测试默认数据源
     *  对于没有做分片处理的操作，则会直接访问默认数据源处理
     */
    @Test
    public void test5(){
        TbLog log = TbLog.builder().id(2l).info("这是一个测试2").build();
        tbLogMapper.insert(log);
    }

    /**
     * @Description 测试查询数据库算法类
     */
    @Test
    public void testPrecisDb(){
        //测试精准查询
//        List<TOrder> infos = tOrderMapper.findByUserId(32l);
        //测试范围查询
        List<TOrder> infos = tOrderMapper.findRangeByUserId(2l, 30l);
        System.out.println(infos);
    }

    /**
     * @Description 测试精准查询表
     */
    @Test
    public void testPrecTb(){
        //此时没有使用userid字段，所以数据库全量查询，但是每个库对应的表只查询t_order_1
        TOrder tOrder = tOrderMapper.selectByPrimaryKey(44l);
        System.out.println(tOrder);
    }

    /**
     * @Description 测试精准匹配表的范围查询
     */
    @Test
    public void testRangeTable(){
        List<TOrder> tOrders = tOrderMapper.selectByRange(1l, 40l);
        System.out.println(tOrders);
    }
    /**
     * @Description
     */
    @Test
    public void testRangeTable2(){
        List<TOrder> tOrders = tOrderMapper.selectByRange2(2l,1l, 40l);
        System.out.println(tOrders);
    }


}

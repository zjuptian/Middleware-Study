package com.tp.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Jedis简单测试
 * Created on 2021/10/19
 *
 * @author Patric Tian
 */
public class InitJedisTest {
    @Test
    public void testJedis() {
        //1. 连接redis
        Jedis jedis = new Jedis("1.116.90.3", 6379);
        //2. 操作redis
        jedis.set("school","zju");
        System.out.println(jedis.get("school"));
        //3. 关闭redis连接
        jedis.close();
    }
}

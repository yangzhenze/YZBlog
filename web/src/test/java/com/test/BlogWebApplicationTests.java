package com.test;
import com.yzz.blog.BlogWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.TimeUnit;

/**
 * @author zzy
 * @Date 2019-10-30 09:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogWebApplication.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class BlogWebApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;


    //@Test
    public void testString(){
        // 设置key为test,值为test
        redisTemplate.opsForValue().set("key","redis");   //set key redis
        // 获取key为test的值
        System.out.println("***************"+redisTemplate.opsForValue().get("key")); //get key
        // 设置key为test1,值为test1,时长为10,时长单位为秒 10秒后失效
        redisTemplate.opsForValue().set("key","redis",10, TimeUnit.SECONDS); // set key redis -> expire key 10


        redisTemplate.opsForValue().set("key","hello world"); //set hello world
        // 覆写给定的key值的字符串，众偏移量开始覆写
        redisTemplate.opsForValue().set("key","redis", 6); //setrange key 6 redis
        System.out.println("***************"+redisTemplate.opsForValue().get("key"));
        // 结果：***************hello redis


        redisTemplate.opsForValue().set("getKey","test"); //set getkey test
        //设置key值并返回旧的值
        System.out.println("***************"+redisTemplate.opsForValue().getAndSet("getKey","test2"));//getset getKey test2


    }
    //@Test
    public void testList(){
        // 将所有指定的值插入存储在键的列表的头部。（从左边插入）
        redisTemplate.opsForList().leftPush("list","java");  // lpush list java
        redisTemplate.opsForList().leftPush("list","python"); // lpush list python
        redisTemplate.opsForList().leftPush("list","java script"); // lpush list java script
        System.out.println(redisTemplate.opsForList().range("list",0,-1)); // lrange list 0 -1
        // 结果：[java script, python, java]

        // 弹出最列表中第一个元素（左边的元素），弹出之后该值在该列表中不复存在
        System.out.println(redisTemplate.opsForList().leftPop("list")); // lpop list
        // 结果: java script

        System.out.println(redisTemplate.opsForList().range("list",0,-1)); // lrange list 0 -1
        // 结果：[python, java]


        // 将所有指定的值插入存储在键的列表的底部。（从右边插入）
        redisTemplate.opsForList().rightPush("rightList","mysql"); // rpush rightList mysql
        redisTemplate.opsForList().rightPush("rightList","oracle"); // rpush rightList oracle
        redisTemplate.opsForList().rightPush("rightList","redis"); // rpush rightList redis
        System.out.println(redisTemplate.opsForList().range("rightList",0,-1)); // lrange list 0 -1
        // 结果：[mysql, oracle, redis]

        // 弹出最列表中最后一个元素（右边的元素），弹出之后该值在该列表中不复存在
        System.out.println(redisTemplate.opsForList().rightPop("rightList")); // rpop rightList
        // 结果 redis

        System.out.println(redisTemplate.opsForList().range("rightList",0,-1)); // lrange list 0 -1
        // 结果：[mysql, oracle]

        //获取集合长度
        System.out.println(redisTemplate.opsForList().size("rightList"));// llen rightList

    }

    //@Test
    public void testHash(){

        //设置散列hashKey的值
        redisTemplate.opsForHash().put("redisHash","name","redis"); // hset redisHash name redis
        redisTemplate.opsForHash().put("redisHash","age",12); // hset redisHash age 12

        //获取散列hashKey的值
        System.out.println(redisTemplate.opsForHash().get("redisHash","name")); // hget redisHash name
        System.out.println(redisTemplate.opsForHash().get("redisHash","age")); // hget redisHash age*/
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        // 结果 {name=redis, age=12}

        //查询该hash的键
        System.out.println(redisTemplate.opsForHash().keys("redisHash")); // hkeys redisHash
        // 结果 [name, age]

        //查询该hash的值
        System.out.println(redisTemplate.opsForHash().values("redisHash")); // hvals redisHash
        // 结果 [redis, 12]

        //查询哈希hashKey是否存在
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash","name")); // hexists redisHash name
        // 结果 true

        //删除该key下对应的hash
        redisTemplate.opsForHash().delete("redisHash","name"); // hdel redisHash name

    }

    //@Test
    public void testSet(){
        // 添加set数据
        redisTemplate.opsForSet().add("set","1"); // sadd set 1
        redisTemplate.opsForSet().add("set","2"); // sadd set 2
        redisTemplate.opsForSet().add("set","3"); // sadd set 3

        // 查看key下的成员
        System.out.println(redisTemplate.opsForSet().members("set")); // smembers set
        // 结果 [2, 3, 1]

        //长度
        System.out.println(redisTemplate.opsForSet().size("set")); // scard set
        // 结果 3

        //删除成员
        redisTemplate.opsForSet().remove("set" ,"2"); // srem set 2

        //移除并返回集合中的一个随机元素
        System.out.println(redisTemplate.opsForSet().pop("set")); // spop set
    }

   @Test
    public void testZSet(){
        // 添加zset数据
        /*redisTemplate.opsForZSet().add("zset","a",1); // zadd zset 1 a
        redisTemplate.opsForZSet().add("zset","b",2); // zadd zset 2 b
        redisTemplate.opsForZSet().add("zset","c",3); // zadd zset 3 c
        redisTemplate.opsForZSet().add("zset","d",5); // zadd zset 5 d
        redisTemplate.opsForZSet().add("zset","e",4); // zadd zset 4 e*/

        //查看 zset数据
        System.out.println(redisTemplate.opsForZSet().range("zset",0,-1)); // zrange set 0 -1
        // 结果 [a, b, c, e, d]

        // 长度
        System.out.println(redisTemplate.opsForZSet().size("zset")); // zcard zset
        // 结果 5

        //计算在有序集合中指定区间分数的成员数
        System.out.println(redisTemplate.opsForZSet().count("zset",1,2));
        // 结果 2

        //倒序查看列表
        System.out.println(redisTemplate.opsForZSet().reverseRange("zset",0,5)); // zrevrangebyscore zset 5 1
        // 结果 [d, e, c, b, a]


       //倒序查看列表并限制返回数据
       System.out.println(redisTemplate.opsForZSet().reverseRangeByScore("zset",0,5,0,3)); // zrevrangebyscore zset 5 1 limit 0 3
       // 结果 [d, e, c]
    }
}

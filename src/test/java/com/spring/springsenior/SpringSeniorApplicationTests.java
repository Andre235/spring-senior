package com.spring.springsenior;

import com.spring.springsenior.cache.entity.Employee;
import com.spring.springsenior.cache.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringSeniorApplicationTests {

    //操作字符串
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //操作对象
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RedisTemplate<Object, Employee> employeeRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testAppend(){
        //stringRedisTemplate.opsForValue().append("data","hello redis");
        System.out.println(stringRedisTemplate.opsForValue().get("data"));
        stringRedisTemplate.opsForList().leftPush("myList","1");
        stringRedisTemplate.opsForList().leftPush("myList","2");
        stringRedisTemplate.opsForList().leftPush("myList","3");
    }

    /**
     * 将对象存储到redis中
     * 自定义redis序列化规则(将对象序列化为JSON)
     */
    @Test
    void addObj(){
        Employee employee = employeeService.findById(2L);
        //String string = JSONObject.toJSONString(employee);
        //redisTemplate.opsForValue().set("emp01",string);
        employeeRedisTemplate.opsForValue().set("emp002",employee);
    }

}

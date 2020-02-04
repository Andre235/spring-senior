package com.spring.springsenior;

import com.spring.springsenior.ElasticSearch.entity.Article;
import com.spring.springsenior.ElasticSearch.repository.ArticleRepository;
import com.spring.springsenior.RabbitMQ.entity.Book;
import com.spring.springsenior.cache.entity.Employee;
import com.spring.springsenior.cache.service.EmployeeService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

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

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 管理RabbitMQ消息组件(交换器、队列等组件)
     */
    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 注入JestClient对ES进行操作
     */
    @Autowired
    JestClient jestClient;

    @Autowired
    ArticleRepository articleRepository;

    /**
     * 通过SpringData elasticsearch方式实现新增一条数据
     */
    @Test
    void index(){
        Article article = new Article(1, "吴承恩", "西游记", "三打白骨精");
        articleRepository.index(article);
    }

    /**
     * 通过JestClient向ES中构建一个索引
     */
    @Test
    void createESIndex() throws IOException {
        //new 一个对象用于构建索引
        Article article = new Article(1, "好消息", "张三", "hello world");
        //构建一个index索引
        Index builder = new Index.Builder(article).index("atguigu").type("news").build();
        jestClient.execute(builder);
    }

    /**
     * 添加一个DirectExchange交换器
     */
    @Test
    void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("direct.exchange"));
    }

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

    /**
     * rabbitMQ发送消息，单播机制
     */
    @Test
    void rabbitMQSendMsg(){
        //这种实现方式需要自己构造Message对象，封装消息头和消息内容
        //rabbitTemplate.send(String exchange,String routingKey,Message message);

        //object默认当成消息体，只需要传入发送的对象，自动序列化发送给RabbitMQ
        HashMap<String, Object> map = new HashMap<>();
        map.put("first msg", Arrays.asList("list",123,true));
        rabbitTemplate.convertAndSend("exchange.direct","atguigu",map);
    }

    /**
     * 发送消息 广播模式
     */
    @Test
    void broadcast(){
        rabbitTemplate.convertAndSend("exchange.finout","",new Book("西游记","吴承恩"));
    }

    /**
     * rabbitMQ接收消息
     * 接收完消息后，该条消息就会被销毁
     */
    @Test
    void receiveMsg(){
        Object atguigu = rabbitTemplate.receiveAndConvert("atguigu");
        System.out.println("数据类型："+atguigu.getClass());
        System.out.println(atguigu);
    }

}

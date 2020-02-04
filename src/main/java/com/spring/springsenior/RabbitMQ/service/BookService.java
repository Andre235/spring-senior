package com.spring.springsenior.RabbitMQ.service;

import com.spring.springsenior.RabbitMQ.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author : 赵静超
 * @date : 2020/2/4
 * @description :
 */
@Service
@Slf4j
public class BookService {

//    @RabbitListener(queues = {"atguigu.emps","atguigu","atguigu.news","gulixueyuan.news"})
//    public void listenMsg(Book book){
//        log.info("Rabbit 监听数据{}",book);
//    }
//
//    @RabbitListener(queues = {"atguigu.emps","atguigu","atguigu.news","gulixueyuan.news"})
//    public void listenMsg02(Message message){
//        log.info("消息头:{}",message.getMessageProperties());
//        log.info("消息体:{}",message.getBody());
//    }

}

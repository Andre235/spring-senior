package com.geek.mission.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/4
 * @description :
 */
@Service
@Slf4j
public class ScheduleService {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Scheduled(cron = "0 0/5 * * * *")
    public void getTime(){
        LocalDateTime now = LocalDateTime.now();
        log.info("当前时间为{}",now);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件标题
        simpleMailMessage.setSubject("当前时间");
        simpleMailMessage.setText("当前时间为："+now.toString());
        simpleMailMessage.setTo("2356430623@qq.com");
        simpleMailMessage.setFrom("andre215000@163.com");

        mailSender.send(simpleMailMessage);
    }
}

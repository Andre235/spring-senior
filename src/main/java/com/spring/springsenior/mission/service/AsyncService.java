package com.spring.springsenior.mission.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/4
 * @description :
 */
@Slf4j
@Service
public class AsyncService {

    @Async
    public void hello() throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程1沉睡2000ms结束...");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程2沉睡2000ms结束...");
        }).start();
    }


}

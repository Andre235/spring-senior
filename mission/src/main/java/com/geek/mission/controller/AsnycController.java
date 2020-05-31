package com.geek.mission.controller;

import com.geek.mission.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/4
 * @description :
 */
@RestController
public class AsnycController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/greet")
    public String greet() throws InterruptedException {
        asyncService.hello();
        return "success";
    }
}

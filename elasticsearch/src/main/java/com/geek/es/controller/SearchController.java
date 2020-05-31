package com.geek.es.controller;

import com.geek.es.utils.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 赵静超
 * @date : 2020/5/31
 * @description :
 */
@RestController
public class SearchController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/beanList")
    public List<String> beanList() {
        return Arrays.asList(applicationContext.getBeanDefinitionNames());
    }

    @PostMapping("/createIndex")
    public Map<String,Object> createIndex(@RequestParam("index") String index){
        return ESUtil.createIndex(index);
    }

    @PostMapping("/addData")
    public Map<String,Object> addData(){
        Map<String,Object> data = new HashMap<>();
        data.put("username","赵静超");
        data.put("password","123456");
        data.put("age",35);
        return ESUtil.addData("test_index5", null, "35", data);
    }
}

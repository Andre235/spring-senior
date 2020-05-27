package com.geek.controller;

import com.geek.dto.CityDTO;
import com.geek.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 赵静超
 * @date : 2020/5/26
 * @description :
 */
@RestController
@RequestMapping("/list")
public class CityController {

    @Value("${swagger.enable}")
    private Boolean status;

    @Autowired
    AreaService areaService;

    @GetMapping("/status")
    public Boolean status(){
        return status;
    }

    @GetMapping("/tree")
    public List<CityDTO> getTree(){
        return areaService.list2Tree();
    }

}

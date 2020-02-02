package com.spring.springsenior.cache.controller;

import com.spring.springsenior.cache.entity.Department;
import com.spring.springsenior.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("findById/{id}")
    public Department findById(@PathVariable("id") Long id){
        return departmentService.findById(id);
    }
}

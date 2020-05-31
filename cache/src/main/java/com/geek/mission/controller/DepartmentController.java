package com.geek.mission.controller;

import com.geek.mission.entity.Department;
import com.geek.mission.service.DepartmentService;
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

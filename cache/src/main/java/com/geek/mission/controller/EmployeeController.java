package com.geek.mission.controller;

import com.geek.mission.entity.Employee;
import com.geek.mission.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/findById/{id}")
    public Employee findById(@PathVariable("id") Long id){
        return employeeService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Employee> findById(){
        return employeeService.findAll();
    }

    @PostMapping("/create")
    public Employee create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Long id){
        employeeService.deleteById(id);
        return "删除数据成功";
    }
}

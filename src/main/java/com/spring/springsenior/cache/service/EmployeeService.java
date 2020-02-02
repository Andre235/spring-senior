package com.spring.springsenior.cache.service;

import com.spring.springsenior.cache.entity.Employee;

import java.util.List;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
public interface EmployeeService {

    Employee findById(Long id);

    List<Employee> findAll();

    Employee create(Employee employee);

    Employee update(Employee employee);

    void deleteById(Long id);
}

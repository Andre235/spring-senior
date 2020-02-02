package com.spring.springsenior.cache.service;

import com.spring.springsenior.cache.entity.Department;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
public interface DepartmentService {

    Department findById(Long id);
}

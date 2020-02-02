package com.spring.springsenior.cache.dao;

import com.spring.springsenior.cache.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {
}

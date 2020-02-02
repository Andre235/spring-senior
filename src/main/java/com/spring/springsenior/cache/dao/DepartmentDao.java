package com.spring.springsenior.cache.dao;

import com.spring.springsenior.cache.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department,Long> {
}

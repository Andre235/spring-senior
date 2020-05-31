package com.geek.mission.dao;

import com.geek.mission.entity.Employee;
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

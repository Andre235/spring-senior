package com.spring.springsenior.cache.service.impl;

import com.spring.springsenior.cache.dao.EmployeeDao;
import com.spring.springsenior.cache.entity.Employee;
import com.spring.springsenior.cache.service.EmployeeService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee findById(Long id) {
        Optional<Employee> optional = employeeDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new ServiceException("该条数据不存在");
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> all = employeeDao.findAll();
        if(CollectionUtils.isEmpty(all)){
            return Collections.emptyList();
        }else{
            return all;
        }
    }

    @Override
    public Employee create(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        Optional<Employee> optional = employeeDao.findById(employee.getId());
        if(optional.isPresent()){
            Employee entity = optional.get();
            BeanUtils.copyProperties(employee,entity);
            employeeDao.save(entity);
            return entity;
        }else{
            throw new ServiceException("请输入正确的id值");
        }
    }

    @Override
    public void deleteById(Long id) {
        employeeDao.deleteById(id);
    }
}

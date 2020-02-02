package com.spring.springsenior.cache.service.impl;

import com.spring.springsenior.cache.dao.DepartmentDao;
import com.spring.springsenior.cache.entity.Department;
import com.spring.springsenior.cache.service.DepartmentService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description :
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;


    @Override
    public Department findById(Long id) {
        Optional<Department> optional = departmentDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new ServiceException("不存在该数据");
        }
    }
}

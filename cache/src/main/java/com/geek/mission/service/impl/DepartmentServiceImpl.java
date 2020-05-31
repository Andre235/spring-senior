package com.geek.mission.service.impl;

import com.geek.mission.dao.DepartmentDao;
import com.geek.mission.entity.Department;
import com.geek.mission.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department findById(Long id) {
        Optional<Department> optional = departmentDao.findById(id);
        if(optional.isPresent()){
            log.info("编号为{}的员工信息为{}",id,optional.get().toString());
            return optional.get();
        }else{
            throw new ServiceException("不存在该数据");
        }
    }
}

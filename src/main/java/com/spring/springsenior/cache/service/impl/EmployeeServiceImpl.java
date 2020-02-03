package com.spring.springsenior.cache.service.impl;

import com.spring.springsenior.cache.dao.EmployeeDao;
import com.spring.springsenior.cache.entity.Employee;
import com.spring.springsenior.cache.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "employee")  //定义公共缓存名称
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * @Cacheable : 注解属性值说明
     * cacheNames：指定缓存组件的名称,该属性可以是数组，将缓存放在多个缓存中
     * key：缓存数据使用的key，默认使用方法参数值，也可以使用SpEL表达式来获取key
     * keyGenerator：自定义key生成策略。key和keyGenerator两个属性二选一即可
     * cacheManager：指定缓存管理器
     * condition：符合条件下才缓存
     * unless：当unless为true时，不进行缓存
     * sync：是否使用异步模式
     */
    @Cacheable(/*cacheNames = "employee",*/key = "#root.args[0]",condition = "#id>0",unless = "#result == null")
    @Override
    public Employee findById(Long id) {
        Optional<Employee> optional = employeeDao.findById(id);
        if(optional.isPresent()){
            log.info("编号为{}的员工信息为{}",id,optional.get().toString());
            return optional.get();
        }else{
            throw new ServiceException("该数据不存在");
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

    /**
     * @CachePut 注解说明：
     *     与@Cacheable注解不同的是，@CachePut是先调用方法，然后再将返回值放在缓存中
     *     需要注意一点，@CachePut注解的key要和@Cacheable注解的key保持一致，否则查不到相应缓存
     */
    @CachePut(/*cacheNames = "employee",*/key = "#result.id")
    @Override
    public Employee update(Employee employee) {
        Optional<Employee> optional = employeeDao.findById(employee.getId());
        if(optional.isPresent()){
            Employee entity = optional.get();
            BeanUtils.copyProperties(employee,entity);
            employeeDao.save(entity);
            log.info("编号为{}的员工修改后的信息为{}",employee.getId(),optional.get().toString());
            return entity;
        }else{
            throw new ServiceException("请输入正确的id值");
        }
    }

    /**
     * @CacheEvict 清除缓存数据注解
     * key：清除指定key的缓存数据
     * allEntries：是否清除所有缓存数据，默认false
     * beforeInvocation：默认是在方法执行之后清空缓存，如果方法出现异常则不会清空缓存
     */
    @CacheEvict(/*cacheNames = "employee",*/key = "#id",beforeInvocation = false)
    @Override
    public void deleteById(Long id) {
        log.info("id为{}的员工信息已在缓存中清除",id);
        //employeeDao.deleteById(id);
    }
}

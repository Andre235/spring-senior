package com.geek.swagger.dao;

import com.geek.swagger.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : 赵静超
 * @date : 2020/5/26
 * @description :
 */
@Repository
public interface CityDao extends JpaRepository<CityEntity,Long> {
}

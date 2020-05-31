package com.geek.swagger.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geek.swagger.dao.CityDao;
import com.geek.swagger.dto.CityDTO;
import com.geek.swagger.entity.CityEntity;
import com.geek.swagger.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 赵静超
 * @date : 2020/5/26
 * @description :
 */
@Service
public class AreaService {

    @Autowired
    private CityDao cityDao;

    public List<CityDTO> list2Tree(){
        List<CityEntity> list = cityDao.findAll();
        JSONArray jsonArray = JsonUtils.listToTree(JSONArray.parseArray(JSON.toJSONString(list)), "deptId", "parentId", "children");
        return JSONObject.parseArray(jsonArray.toJSONString(), CityDTO.class);
    }
}

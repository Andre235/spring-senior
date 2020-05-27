package com.geek.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geek.dao.CityDao;
import com.geek.dto.CityDTO;
import com.geek.entity.CityEntity;
import com.geek.utils.JsonUtils;
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

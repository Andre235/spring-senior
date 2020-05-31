package com.geek.swagger.dto;

import lombok.Data;

import java.util.List;

/**
 * @author : 赵静超
 * @date : 2020/5/26
 * @description :
 */
@Data
public class CityDTO {
    private Long id;
    private String deptId;
    private String parentId;
    private String name;
    private List<CityDTO> children;
}

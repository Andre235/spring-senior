package com.geek.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;

/**
 * @author : 赵静超
 * @date : 2020/5/9
 * @description :
 */
@Data
public class PagerDto {

    @ApiModelProperty(value = "页码", required = true)
    private int page;

    @ApiModelProperty(value = "每页条数", required = true)
    private int size;

    private HashMap map;
}

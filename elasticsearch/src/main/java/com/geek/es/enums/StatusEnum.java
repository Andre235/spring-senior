package com.geek.es.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : 赵静超
 * @date : 2020/5/31
 * @description :
 */
@AllArgsConstructor
public enum StatusEnum {

    EXIST("101","数据存在"),
    NOT_EXIST("401","数据不存在"),
    OPERATION_SUCCESS("200","操作成功"),
    OPERATION_FAIL("402","操作失败");

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String msg;
}

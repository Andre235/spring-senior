package com.geek.mission;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author : 赵静超
 * @date : 2020/5/10
 * @description :
 */
@SpringBootTest
public class TESTAPP {

    private Integer id = null;
    @Test
    public void test(){
        if( id == null){
            throw new IllegalArgumentException("id 不能为空");
        }
        Assert.notNull(id,"id 不能为空");

    }
}

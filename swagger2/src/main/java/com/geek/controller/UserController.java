package com.geek.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author : 赵静超
 * @date : 2020/5/9
 * @description :
 */
@RestController
@RequestMapping("/api/v1/users")
@Api(value = "User API接口",tags = "user")
public class UserController {

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public String login(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
                        @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password) {
        return "{'username':'" + username + "', 'password':'" + password + "'}";
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "header", name = "phone", required = true, value = "手机号"),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "query", name = "nickname", required = true, value = "nickname", defaultValue = "双击666"),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "path", name = "platform", required = true, value = "平台", defaultValue = "PC"),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "body", name = "password", required = true, value = "密码")
    })
    @PutMapping(value = "/{platform}/register")
    public String register(@RequestHeader String phone, @RequestParam String nickname, @PathVariable String platform, @RequestBody String password){
        return "{'username':'" + phone + "', 'nickname':'" + nickname + "', 'platform': '" + platform + "', 'password':'"+password+"'}";
    }

    @ApiOperation(value = "用户列表", notes = "查询用户列表")
    @GetMapping(value = "/list")
    public String getUserList(){
        return "获取用户列表成功";
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping("delete/{id}")
    public String removeUser(@PathVariable Long id){
        return "成功删除id为"+id+"的用户";
    }

}

package com.homeworkh.usermanagementserver.controller;

import com.homeworkh.usermanagementserver.common.RestResponse;
import com.homeworkh.usermanagementserver.entity.User;
import com.homeworkh.usermanagementserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lq
 * @date 2020/12/21 16:44
 */
@RestController(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public RestResponse saveUser(@RequestBody Map<String,Object> map){
        int i = userService.addUser(map);
        if (i>0) return RestResponse.ok("添加成功",map);
        return RestResponse.error(500,map.toString());
    }

    @PutMapping(value = "/modify")
    public RestResponse modifyUser(@RequestBody Map<String,Object> map){
        int i = userService.modifyUser(map);
        if (i>0) return RestResponse.ok("修改成功",map);
        return RestResponse.error(500,map.toString());
    }

    @DeleteMapping(value = "/del")
    public RestResponse delUser(@RequestParam Integer id){
        int i = userService.deleteUser(id);
        if (i>0) return RestResponse.ok("删除成功",id);
        return RestResponse.error(500,String.valueOf(id));
    }

    @GetMapping(value = "/userList")
    public RestResponse getUserList(){
        List<User> users = userService.queryUserInfo();
        if (users.size()>0) return RestResponse.ok("查询成功",users);
        return RestResponse.error(500,"查询失败");
    }
}

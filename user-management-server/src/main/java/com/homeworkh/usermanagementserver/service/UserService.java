package com.homeworkh.usermanagementserver.service;

import com.homeworkh.usermanagementserver.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author lq
 * @date 2020/12/21 16:01
 */
public interface UserService {
    int addUser(Map<String,Object> map);
    int deleteUser(int userId);
    List<User> queryUserInfo();
    int modifyUser(Map<String,Object> map);
}

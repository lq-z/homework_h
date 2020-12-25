package com.homeworkh.usermanagementserver.service.impl;

import com.homeworkh.usermanagementserver.entity.User;
import com.homeworkh.usermanagementserver.mapper.UserMapper;
import com.homeworkh.usermanagementserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lq
 * @date 2020/12/21 16:05
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(Map<String,Object> map) {
        return userMapper.addUser(map);
    }

    @Override
    public int deleteUser(int userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    public List<User> queryUserInfo() {
        return userMapper.queryUser();
    }

    @Override
    public int modifyUser(Map<String,Object> map) {
        return userMapper.updateUser(map);
    }
}

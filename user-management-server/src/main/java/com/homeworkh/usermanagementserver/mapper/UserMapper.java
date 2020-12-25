package com.homeworkh.usermanagementserver.mapper;

import com.homeworkh.usermanagementserver.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

/**
 * @author lq
 * @date 2020/12/18 23:11
 */
@Mapper
public interface UserMapper {

    List<User> queryUser();

    int addUser(Map<String,Object> map);

    int updateUser(Map<String,Object> map);

    int deleteUser(int userId);
}

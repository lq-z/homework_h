package com.homeworkh.usermanagementserver.entity;

import lombok.Data;

/**
 * @author lq
 * @date 2020/12/18 22:56
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String nickName;
    private String password;
    private String number;
}

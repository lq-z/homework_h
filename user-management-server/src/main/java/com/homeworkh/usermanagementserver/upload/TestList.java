package com.homeworkh.usermanagementserver.upload;


import com.homeworkh.usermanagementserver.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lq
 * @date 2021/1/6 23:51
 */
public class TestList {

    public static void main(String[] args) {

        List<User> list = new ArrayList<>();
        for (int i=0;i<20;i++){
            User user = new User();
            user.setNumber("181"+i);
            list.add(user);
        }

        List<String> listString = new ArrayList<>();
        for (int j=1;j<15;j++){
            listString.add("181"+j);
        }


        for (int k=0;k<list.size();k++){
            if (!listString.contains(list.get(k).getNumber())){
                System.out.println("不在listString中的元素:"+list.get(k).getNumber());
            }
        }


    }

    }

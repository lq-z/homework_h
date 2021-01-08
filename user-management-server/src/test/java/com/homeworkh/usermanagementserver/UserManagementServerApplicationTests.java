package com.homeworkh.usermanagementserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class UserManagementServerApplicationTests {

//    @Test
//    void contextLoads() {
//    }
//

    @Test
    public void test(){

//        ExecutorService executorService = Executors.newFixedThreadPool(5);//无界队列 LinkedBlockingQueue
//        Executors.newSingleThreadExecutor();//无界队列 LinkedBlockingQueue
//        ExecutorService excel = Executors.newCachedThreadPool(); //SynchronousQueue
//        Executors.newScheduledThreadPool(1);

//        ReentrantLock lock = new ReentrantLock();
//        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
//        Map<String,Object>  map = new HashMap<>();
//        map.put(null,null);
//
//        System.out.println(map);
//
//        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String,Object>();

        //spring的组件？ core context beans
        //spring的代理模式实现原理？
        //zookeeper的原理 可以用作？
        //jvm的内存回收机制
        //设计模式
        //代理模式 uml图
        //epoll 、pooll 、select 区别
        //elk
        //范式
        //vue生命周期
        //微服务的优点与缺点


        StringBuffer  sb = new StringBuffer();
        String a = "a";
        String b = "b";
        sb.append(a).append("|").append(b).append("|").append(0);

        System.out.println(sb);

        List<String> al = new ArrayList<>();
        al.add(sb.toString());

        System.out.println(al);
    }


}

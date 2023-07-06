package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

//持有用户信息，用于代替session对象
//因为每一个请求，都是一个线程，每个线程应该对应自身的用户信息,由于由IOC容器管理的对象是单例的，因此为实现多线程之间的隔离互不影响，这里采用ThreadLocal,为每一个线程都分配一个user
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<User>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}

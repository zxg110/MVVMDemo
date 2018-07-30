package com.practice.zengxiangge.mvvmdemo;

/**
 * Created by zengxiangge on 2018-5-6.
 */

public class LoginModel {

    public UserBean getCurrentUser(){
        //模拟从数据库中读取最近登录用户
        UserBean user = new UserBean();
        user.getUsername().set("init usernamedsfdsaf");
        user.setEmail("123455@qq.com");
        return user;
    }

    public void login(UserBean userBean,String password,ResultListener listener){
        //模拟网络通信登录
        listener.onSuccess();
    }
}

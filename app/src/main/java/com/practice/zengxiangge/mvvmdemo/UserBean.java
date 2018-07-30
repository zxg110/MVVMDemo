package com.practice.zengxiangge.mvvmdemo;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by zengxiangge on 2018-5-6.
 */

public class UserBean {
    private String email;

    private ObservableField<String> username = new ObservableField<>();

    public UserBean(){}

    public ObservableField<String> getUsername() {
        return username;
    }

    public void setUsername(ObservableField<String> username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDataCompleted(){
        return username != null && email != null;
    }
}

package com.practice.zengxiangge.mvvmdemo;

import android.databinding.BaseObservable;

import java.util.Objects;

/**
 * Created by zengxiangge on 2018-5-6.
 */

public class BindableString extends BaseObservable {
    String value;

    public String get() {
        return value != null ? value : "";
    }

    public void set(String value) {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            notifyChange();
        }
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}

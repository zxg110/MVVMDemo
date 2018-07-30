package com.practice.zengxiangge.mvvmdemo;

/**
 * Created by zengxiangge on 2018-5-6.
 */

public interface ResultListener {
    void onSuccess();
    void onError(Object error);
}

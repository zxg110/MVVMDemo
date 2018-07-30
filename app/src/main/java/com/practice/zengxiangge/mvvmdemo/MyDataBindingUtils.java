package com.practice.zengxiangge.mvvmdemo;

import android.app.Activity;
import android.databinding.Observable;
import android.util.ArrayMap;

/**
 * Created by zengxiangge on 2018-5-6.
 */

public class MyDataBindingUtils {
    private static ArrayMap<Activity, ArrayMap<Observable, Observable.OnPropertyChangedCallback>> commonMap = new ArrayMap<>();
    public static void addCallBack(Activity activity,
                                   Observable observable, Observable.OnPropertyChangedCallback callback) {
        ArrayMap<Observable, Observable.OnPropertyChangedCallback> callbackArrayMap = commonMap.get(activity);
        if (callbackArrayMap == null) {
            callbackArrayMap = new ArrayMap<>();
            commonMap.put(activity, callbackArrayMap);
        }
        observable.addOnPropertyChangedCallback(callback);
        callbackArrayMap.put(observable, callback);
    }

    public static void removeCallBack(Activity activity) {
        ArrayMap<Observable, Observable.OnPropertyChangedCallback> callbackArrayMap = commonMap.get(activity);
        if (callbackArrayMap != null) {
            for (Observable observable : callbackArrayMap.keySet()) {
                observable.removeOnPropertyChangedCallback(callbackArrayMap.get(observable));
            }
        }
    }


}

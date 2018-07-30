package com.practice.zengxiangge.mvvmdemo;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.practice.zengxiangge.mvvmdemo.databinding.ActivityLoginBinding;

public class LoginActivity extends Activity {

    //持有viewModel(非必须)
    private LoginViewModel viewModel;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        viewModel = new LoginViewModel(this);
        binding.setLoginViewModel(viewModel);
        viewModel.loadCurrentUser();
//        binding.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
//            @Override
//            public void onPropertyChanged(Observable observable, int i) {
//
//            }
//        });
        context = this;
        /**
         * MVVM思想，以数据为中心，监听数据变化做出反应。也可以使用传统方法，将Activity作为回调接口传给ViewModel,
         * 在Model回调中直接调用回调
         */
        MyDataBindingUtils.addCallBack(this, viewModel.ObservableResultPrompt, new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Toast.makeText(context,"observable:"+((ObservableField<String>)observable).get()+","+i,Toast.LENGTH_SHORT).show();
            }
        });

        // Set up the login form.
    }
}


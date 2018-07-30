package com.practice.zengxiangge.simpledemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.practice.zengxiangge.mvvmdemo.R;
import com.practice.zengxiangge.mvvmdemo.databinding.ActivitySimpleBinding;

/**
 * Created by zengxiangge on 2018-5-7.
 */

public class SimpleDemoActivity extends Activity{
    private ActivitySimpleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple);
        setData();
    }
    /**
     * 我们获取数据后将数据set给binding即可，不需要findViewById获取控件引用
     * 并通过引用进行setText()等更新UI操作。其实这些事情是生成的binding帮我们做的
     * 数据更新后，需要再次调用binding.setXXX更新数据
     *
     * 但是，这和之前MVP的方式差别并不大，同样需要我们手动set。只不过以前是最原始的方式set
     * 现在通过binding这个代理帮我们做，我们省了很多垃圾代码。
     *
     * 在这个例子中，有三点MVVM框架要求的目标我们没有达到：
     * 1、我们只为binding绑定了数据，并没有绑定命令监听UI
     * 2、没有实现动态绑定，数据更新后，我们需要再次调用binding.setXXX()
     * 3、没有实现双向绑定，例如EditText中显示了我们set的entity.data_1数据，
     * 用户在EditText中更改了数据，我们打印entity.data_1，依然是旧数据
     *
     * 针对上述三点，我们提出如下方案：
     * 将数据与命令(与业务模块的交互)封装成一个ViewModel角色，并实现动态、双向绑定，将ViewModel
     * set给binding。这样便一劳永逸。在Login例子中我们来实现。
     *
     */
    private String strData = "strData";
    private int intData = 1000;
    private void setData(){
        binding.setStrData(strData);
        binding.setIntData(intData);
        Entity entity = new Entity();
        entity.setData_1("entityData_1");
        entity.setData_2(2000);
        entity.setData_3(true);
        binding.setEntity(entity);
    }
}

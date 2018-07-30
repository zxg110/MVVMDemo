package com.practice.zengxiangge.mvvmdemo;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by zengxiangge on 2018-5-6.
 * 封装数据信息，封装与model的交互；通过DataBinding技术与View进行绑定，包括数据绑定和命令绑定
 * 具体做法是
 * step1:布局文件中<data>标签中引入该类并与控件进行绑定
 * step2:将ViewModel set给binding。
 *
 * 这样便做到了ViewModel不引用任何控件，不编写根据数据更新UI的代码
 * 只需要编写调用Model代码即可(业务逻辑)
 * 其实是binding角色持有控件引用，帮我们做了更新UI的事情
 */

/**
 * note
 * ‘@={}’ 配合ObservableField使用可实现动态双向绑定
 * ‘@={}’与原始类型String 配合使用，可正确从控件获取数据，当更新数据，控件内容不会随之改变
 * 同样需要binding.setXXX()
 * 单独使用原始数据String，只能第一次正确显示数据，该方法需要每次数据更新手动bingding.setXXX()显示才可以更新
 * 并且并不能从控件中获取数据，相当于单向绑定
 * 单独使用ObervableField，不能正确从控件中获取数据。但当数据更新，控件内容可以随之更新
 *
 */
@Parcel
public class LoginViewModel{
    //持有Model，通过Model调用业务方法
    private LoginModel loginModel;
    //封装数据信息
    public UserBean userBean;
    public String passWord;

    public ObservableField<String> bindablePassWord = new ObservableField<>();

    public String resultPrompt;

    public ObservableField<String> ObservableResultPrompt = new ObservableField<String>();

    public Context context;

    public ObservableArrayList<String> nameList = new ObservableArrayList<>();

    public LoginViewModel(Context context){
        loginModel = new LoginModel();
        this.context = context;
    }

    public void onBtnClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.login_btn:
                goLogin();
                break;
            case R.id.add_btn:
                goAdd();
                break;
        }

    }

    private void goLogin(){
        ObservableResultPrompt.set("waiting");
        if(userBean.isDataCompleted() && passWord != null ){
            loginModel.login(userBean, passWord, new ResultListener() {
                @Override
                public void onSuccess() {
                    //成功回调，操作数据即可，不需要手动更新UI
                    ObservableResultPrompt.set("login success!!");
                    userBean.getUsername().set("username after login");
                    userBean.setEmail("email after Login");
                    resultPrompt = "login success";
                }

                @Override
                public void onError(Object error) {
                    resultPrompt = "login fail";
                }
            });
        }
    }


    private void goAdd(){
        //仅操作数据，不调用操作UI的方法
        nameList.add("zxg");
    }

    public void loadCurrentUser(){
        userBean = loginModel.getCurrentUser();
  //      ObservableResultPrompt.set("load success");
    }

    /**
     * BindingAdapter
     * 简单的更新UI，例如更新TextView的text，我们可以将数据直接与text属性绑定
     * 但是一些复杂的更新UI，例如自定义控件、动态添加View等，我们不再能通过简单的控件属性与数据绑定来实现动态更新
     * 而是采用BindAdapter的方式。通过BindAdapter注解，将控件和复杂的数据类型绑定，并编写实现操作UI逻辑
     * 但是，我们不会直接调用这些方法，因为已经与数据绑定，数据变化，DataBinding框架会帮我们调用，具体的是生成的
     * Binding类帮我们调用。这也再一次证实了MVVM架构一切以数据为中心。
     * 还有一些高级特性，例如列表绑定、BindingMethods等用到时再继续学习
     * https://www.jianshu.com/p/40093dd0245f
     */
    @BindingAdapter({"bind:nameList","bind:context"})
    public static void setNameList(ViewGroup linearLayout, ArrayList<String> nameList,Context context){
        linearLayout.removeAllViews();

        for (String s : nameList) {
            TextView t = new TextView(context);
            t.setText(s);
            linearLayout.addView(t);
        }
    }


}

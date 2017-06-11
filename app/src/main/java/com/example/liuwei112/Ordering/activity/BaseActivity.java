package com.example.liuwei112.Ordering.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.liuwei112.myfood.R;

/**
 * Created by songedwin on 2017/5/10.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected int layout_file= R.layout.activity_main;
    abstract void initViews();
    abstract void initEvents();
    abstract void initData();
    void setLayout(int layout_file)
    {
        setContentView(layout_file);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(layout_file);
        initViews();
        initEvents();
        initData();
    }
    public int getUser_id()
    {
        SharedPreferences pref = getSharedPreferences("userInfo",MODE_PRIVATE);
        int user_id = pref.getInt("user_id",0);//第二个参数为默认值
        return user_id;
    }


}

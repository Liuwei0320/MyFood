package com.example.liuwei112.Ordering.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liuwei112.Ordering.adapter.UserOrderAdapter;
import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOrderActivity extends BaseActivity {
    RecyclerView uo_rv;

    @Override
    void initViews() {
        setLayout(R.layout.activity_user_order);
        uo_rv = (RecyclerView) findViewById(R.id.uo_rv);
        uo_rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    void initEvents() {

    }

    @Override
    void initData() {
        UserModel userModel = new UserModel();
        Call call = userModel.getAllUserOrder(getUser_id());
        Callback<List<OrderBean>> callback = new Callback<List<OrderBean>>() {
            @Override
            public void onResponse(Call<List<OrderBean>> call, Response<List<OrderBean>> response) {
                List<OrderBean> mDataList = response.body();
                uo_rv.setAdapter(new UserOrderAdapter(UserOrderActivity.this, mDataList));//设置适配器
            }

            @Override
            public void onFailure(Call<List<OrderBean>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }
}


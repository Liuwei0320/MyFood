package com.example.liuwei112.Ordering.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.liuwei112.Ordering.adapter.OrderAdapter;
import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.Ordering.model.FoodModel;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodCommentActivity extends BaseActivity {
    RecyclerView recyclerView;
    int food_id;
    TextView order_name;

    @Override
    void initViews() {
        setLayout(R.layout.activity_food_comment);
        order_name = (TextView)findViewById(R.id.order_name);
        recyclerView = (RecyclerView)findViewById(R.id.ovv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    void initEvents() {

    }

    @Override
    void initData() {
        Bundle bundle = getIntent().getExtras();
        food_id = bundle.getInt("food_id");
        order_name.setText(bundle.getString("food_name"));


        FoodModel foodModel = new FoodModel();
        Call call = foodModel.getAllUserFoodOrder(food_id);
        Callback<List<OrderBean>> callback = new Callback<List<OrderBean>>() {
            @Override
            public void onResponse(Call<List<OrderBean>> call, Response<List<OrderBean>> response) {
                List<OrderBean> mDataList = response.body();
                recyclerView.setAdapter(new OrderAdapter(FoodCommentActivity.this, mDataList));//设置适配器
            }

            @Override
            public void onFailure(Call<List<OrderBean>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }
}

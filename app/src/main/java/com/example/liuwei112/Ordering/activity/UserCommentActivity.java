package com.example.liuwei112.Ordering.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liuwei112.Ordering.adapter.UserCommentAdapter;
import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCommentActivity extends BaseActivity {
    RecyclerView uc_rv;

    private UserCommentAdapter userCommentAdapter;
    @Override
    void initViews() {
        setLayout(R.layout.activity_user_comment);
        uc_rv =(RecyclerView)findViewById(R.id.uc_rv);
        uc_rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    void initEvents() {

    }

    @Override
    void initData() {
        UserModel userModel = new UserModel();
        Call call = userModel.getAllUserComment(getUser_id());
        Callback<List<OrderBean>> callback = new Callback<List<OrderBean>>() {
            @Override
            public void onResponse(Call<List<OrderBean>> call, Response<List<OrderBean>> response) {
                List<OrderBean> mDataList = response.body();
                userCommentAdapter = new UserCommentAdapter(UserCommentActivity.this, mDataList);
                uc_rv.setAdapter(userCommentAdapter);//设置适配器

            }

            @Override
            public void onFailure(Call<List<OrderBean>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }

}


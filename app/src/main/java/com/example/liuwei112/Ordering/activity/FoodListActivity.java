package com.example.liuwei112.Ordering.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwei112.Ordering.adapter.ShopContentAdapter;
import com.example.liuwei112.Ordering.bean.ShopBean;
import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.model.FoodModel;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodListActivity extends BaseActivity {
    RecyclerView recyclerView;
    TextView shopname;
    ImageButton shop_collected;
    TextView shop_tele;
    int shop_id;
    String status;

    void initViews()
    {
        //设置布局文件
        setLayout(R.layout.activity_foodlist);

        shopname = (TextView)findViewById(R.id.shop_name);
        shop_tele = (TextView)findViewById(R.id.shop_tele);
        recyclerView = (RecyclerView)findViewById(R.id.rvv);
        shop_collected=(ImageButton)findViewById(R.id.shop_collected);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));// //设置为垂直布局，这也是默认的
    }

    void initEvents()
    {
        shop_collected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collect();

            }
        });

    }
    private void collect()
    {
        FoodModel foodModel = new FoodModel();
        Call call = foodModel.userCollectShop(getUser_id(),shop_id);
        Callback<SuccessBean> callback = new Callback<SuccessBean>() {
            @Override
            public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                SuccessBean success =response.body();
                if (success.getSuccess().toString().equals("1"))
                {
                    if (status.equals("0"))
                    {
                        shop_collected.setImageResource(R.drawable.collected_2);
                        Toast.makeText(FoodListActivity.this, "收藏成功", Toast.LENGTH_LONG).show();
                        status="1";
                    }
                    else
                    {
                        shop_collected.setImageResource(R.drawable.collected_1);
                        Toast.makeText(FoodListActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        status="0";
                    }
                }
                else
                {
                    Toast.makeText(FoodListActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SuccessBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }
    void initData()
    {
        Bundle bundle = getIntent().getExtras();
        shop_id = bundle.getInt("shop_id");

        getfoodlist();//得到店铺美食列表
        iscollected();//是否收藏
        getShopContent();//店铺详情
    }


    private void getfoodlist()
    {
        UserModel userModel = new UserModel();
        Call call = userModel.shopContent(shop_id);


        Callback<List<ShopBean>> callback=new Callback<List<ShopBean>>() {
            @Override
            public void onResponse(Call<List<ShopBean>> call, Response<List<ShopBean>> response) {
                List<ShopBean> mDataList = (List<ShopBean>) response.body();
                recyclerView.setAdapter(new ShopContentAdapter(FoodListActivity.this, mDataList));//设置适配器
            }

            @Override
            public void onFailure(Call<List<ShopBean>> call, Throwable t) {

            }
        };

        call.enqueue(callback);
    }
    private void iscollected() {
        final FoodModel foodModel = new FoodModel();
        Call call = foodModel.isCollected(getUser_id(), shop_id, 0);
        Callback<SuccessBean> iscollected = new Callback<SuccessBean>() {
            @Override
            public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                SuccessBean result = (SuccessBean) response.body();
                status = result.getCollected().toString();
                if (status.equals("0"))
                {
                    shop_collected.setImageResource(R.drawable.collected_1);
                }
                else
                {
                    shop_collected.setImageResource(R.drawable.collected_2);
                }
            }

            @Override
            public void onFailure(Call<SuccessBean> call, Throwable t) {

            }
        };
        call.enqueue(iscollected);

    }
    private void getShopContent()
    {
        FoodModel foodModel = new FoodModel();
        Call call = foodModel.getShopById(shop_id);
        Callback<ShopBean> callback = new Callback<ShopBean>() {
            @Override
            public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                ShopBean result =response.body();
                shopname.setText(result.getShopname().toString());
                shop_tele.setText(result.getPhonenum()+"");
            }

            @Override
            public void onFailure(Call<ShopBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }


}

















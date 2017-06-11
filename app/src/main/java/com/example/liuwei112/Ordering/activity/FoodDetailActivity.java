package com.example.liuwei112.Ordering.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwei112.Ordering.bean.FoodContentBean;
import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.model.FoodModel;
import com.example.liuwei112.myfood.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends BaseActivity {
    TextView f_name,f_price,f_intro;
    ImageView f_img;
    Button f_comment,f_buy;
    ImageButton f_collected;
    int food_id;
    String internetUrl;
    String status;
    String food_name;
    int food_price,shop_id;
    @Override
    void initViews() {
        setLayout(R.layout.activity_food_detail);
        f_name= (TextView) findViewById(R.id.f_name);
        f_price= (TextView) findViewById(R.id.f_price);
        f_intro= (TextView) findViewById(R.id.f_intro);
        f_img = (ImageView) findViewById(R.id.f_img);
        f_comment=(Button) findViewById(R.id.f_comment);
        f_buy=(Button) findViewById(R.id.f_buy);
        f_collected = (ImageButton) findViewById(R.id.f_collected);

    }

    @Override
    void initEvents() {
        f_collected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collect();
            }
        });

        f_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodDetailActivity.this,FoodCommentActivity.class);
                intent.putExtra("food_id",food_id);
                intent.putExtra("food_name",food_name);
                startActivity(intent);
            }
        });
        f_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(FoodDetailActivity.this,FoodOrderActivity.class);
                intent2.putExtra("food_id",food_id);
                intent2.putExtra("shop_id",shop_id);
                intent2.putExtra("food_name",food_name);
                intent2.putExtra("food_price",food_price);
                startActivity(intent2);
            }
        });

    }

    @Override
    void initData() {
        Bundle bundle = getIntent().getExtras();
        food_id = bundle.getInt("food_id");
        showFoodInfo();
        iscollected();
    }


    private void showFoodInfo()
    {
        FoodModel foodModel = new FoodModel();
        Call call = foodModel.getFoodById(food_id);
        Callback<FoodContentBean> callback = new Callback<FoodContentBean>() {
            @Override
            public void onResponse(Call<FoodContentBean> call, Response<FoodContentBean> response) {
                FoodContentBean data=response.body();
                food_name=data.getFoodname().toString();//为了传值
                shop_id=data.getShop_id();//为了传值
                food_price=data.getPrice();//为了传值
                f_name.setText(data.getFoodname());
                f_price.setText(data.getPrice()+"");
                f_intro.setText(data.getIntro());
                internetUrl=data.getPic().toString();
                if(!data.getPic().isEmpty())
                {
                    Picasso
                            .with(FoodDetailActivity.this)
                            .load(internetUrl)
                            .into(f_img);}
                else
                {
                    f_img.setImageResource(R.drawable.wu);
                }

            }

            @Override
            public void onFailure(Call<FoodContentBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }
    private void iscollected()
    {
        final FoodModel foodModel = new FoodModel();
        Call call = foodModel.isCollected(getUser_id(), food_id, 1);
        Callback<SuccessBean> iscollected = new Callback<SuccessBean>() {
            @Override
            public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                SuccessBean result = (SuccessBean) response.body();
                status = result.getCollected().toString();
                if (status.equals("0"))
                {
                    f_collected.setImageResource(R.drawable.collected_1);
                }
                else
                {
                    f_collected.setImageResource(R.drawable.collected_2);
                }
            }

            @Override
            public void onFailure(Call<SuccessBean> call, Throwable t) {

            }
        };
        call.enqueue(iscollected);

    }
    private void collect()
    {
        FoodModel foodModel = new FoodModel();
        Call call = foodModel.userCollectFood(getUser_id(),food_id);
        Callback<SuccessBean> callback = new Callback<SuccessBean>() {
            @Override
            public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                SuccessBean success =response.body();
                if (success.getSuccess().toString().equals("1"))
                {
                    if (status.equals("0"))
                    {
                        f_collected.setImageResource(R.drawable.collected_2);
                        Toast.makeText(FoodDetailActivity.this, "收藏成功", Toast.LENGTH_LONG).show();
                        status="1";
                    }
                    else
                    {
                        f_collected.setImageResource(R.drawable.collected_1);
                        Toast.makeText(FoodDetailActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        status="0";
                    }
                }
                else
                {
                    Toast.makeText(FoodDetailActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SuccessBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }


}

package com.example.liuwei112.Ordering.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwei112.Ordering.bean.ShopBean;
import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.bean.UserBean;
import com.example.liuwei112.Ordering.model.FoodModel;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodOrderActivity extends BaseActivity {
    int food_id;
    int food_price,shop_id;
    TextView buy_name,buy_adress,buy_foodname,buy_shopname,buy_price,buy_num,buy_tele;
    EditText buy_time;
    ImageButton buy_remove,buy_add;
    Button buy_buy;
    double total;

    @Override
    void initViews() {
        //设置布局文件
        setLayout(R.layout.activity_food_order);
        buy_name =(TextView)findViewById(R.id.buy_name);
        buy_adress =(TextView)findViewById(R.id.buy_adress);
        buy_foodname =(TextView)findViewById(R.id.buy_foodname);
        buy_shopname =(TextView)findViewById(R.id.buy_shopname);
        buy_price =(TextView)findViewById(R.id.buy_price);
        buy_num=(TextView)findViewById(R.id.buy_num);
        buy_remove =(ImageButton)findViewById(R.id.buy_remove);
        buy_add = (ImageButton)findViewById(R.id.buy_add);
        buy_buy = (Button)findViewById(R.id.buy_buy);
        buy_tele =(TextView)findViewById(R.id.buy_tele);
        buy_time =(EditText)findViewById(R.id.buy_time);
    }

    @Override
    void initEvents() {
        imageButton();
        purchase();
    }

    private void imageButton()
    {
        //点击加，数量加1
        buy_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_purchase_num = buy_num.getText().toString();
                double price = Double.parseDouble(food_price+"");
                int num = Integer.parseInt(str_purchase_num) + 1;
                total = price * num;
                buy_num.setText("" + num);
                buy_price.setText("" + total);

            }
        });
        //点击减，数量减1，但是不能小于1；
        buy_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_purchase_num = buy_num.getText().toString();
                if (Integer.valueOf(buy_num.getText().toString())<=1)
                {
                    Toast.makeText(FoodOrderActivity.this,"数量不能小于1",Toast.LENGTH_SHORT).show();
                }
                else{
                    double price = Double.parseDouble(food_price+"");
                    int num = Integer.parseInt(str_purchase_num) - 1;
                    total = price * num;
                    buy_num.setText("" + num);
                    buy_price.setText("" + total);

                }
            }
        });

    }

    @Override
    void initData() {
        Bundle bundle = getIntent().getExtras();
        food_price = bundle.getInt("food_price");
        shop_id =bundle.getInt("shop_id");//获取店铺信息
        food_id=bundle.getInt("food_id");
        buy_foodname.setText(bundle.getString("food_name"));
        buy_price.setText(food_price+"");

        getShopContent();//获取店铺详情
        getUserContent();//获取用户信息


    }
    private void getShopContent()
    {
        FoodModel foodModel = new FoodModel();
        Call call = foodModel.getShopById(shop_id);
        Callback<ShopBean> callback = new Callback<ShopBean>() {
            @Override
            public void onResponse(Call<ShopBean> call, Response<ShopBean> response) {
                ShopBean result =response.body();
                buy_shopname.setText(result.getShopname().toString());
            }

            @Override
            public void onFailure(Call<ShopBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }
    private void getUserContent()
    {
        UserModel userModel = new UserModel();
        Call call = userModel.getUserById(getUser_id());
        Callback<UserBean> callback1 =new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean result2 =response.body();
                buy_name.setText(result2.getUsername().toString());
                buy_tele.setText(result2.getMobilenum().toString());
                buy_adress.setText(result2.getAddress().toString());
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        };
        call.enqueue(callback1);
    }
    private void purchase() {

        buy_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(buy_num.getText().toString());
                String b = buy_time.getText().toString();

                UserModel userModel = new UserModel();
                Call call = userModel.insertOrder(getUser_id(),food_id,a,total,b);
                Callback<SuccessBean> callback =new Callback<SuccessBean>() {
                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                        SuccessBean result3 =response.body();
                        if (result3.getSuccess().equals("1"))
                        {
                            //Intent intent = new Intent(FoodOrderActivity.this, MyOrderActivity.class); startActivity(intent);
                            Toast.makeText(FoodOrderActivity.this, "购买成功", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(FoodOrderActivity.this, "购买失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessBean> call, Throwable t) {

                    }
                };
                call.enqueue(callback);

            }
        });

    }
}

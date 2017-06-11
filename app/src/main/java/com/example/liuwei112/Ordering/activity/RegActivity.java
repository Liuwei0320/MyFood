package com.example.liuwei112.Ordering.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.liuwei112.myfood.R.id.pass;

    public class RegActivity extends BaseActivity {
    EditText username,password,mobilenum,adress,comment;//布局声明
    Button back,reg;


    public void initViews()//布局初始化
    {
        //设置布局文件
        setLayout(R.layout.activity_reg);
        username = (EditText)findViewById(R.id.reg_name);
        password =(EditText)findViewById(pass);
        mobilenum = (EditText)findViewById(R.id.mobilenum);
        adress = (EditText)findViewById(R.id.adress);
        comment = (EditText)findViewById(R.id.comment);
        back = (Button)findViewById(R.id.back);
        reg = (Button)findViewById(R.id.reg);
    }
    public  void initEvents()
    {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取UserModel
                UserModel userModel = new UserModel();
                Call call = userModel.userReg(username.getText().toString(),
                        password.getText().toString(),
                        mobilenum.getText().toString(),
                        adress.getText().toString(),
                        comment.getText().toString());//发送请求
                Callback<SuccessBean> callback=new Callback<SuccessBean>(){//处理结果

                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                        String success = response.body().getSuccess();

                        if ("0".equals(success))
                        {
                            //处理失败
                            Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            //成功intent跳转到loginActivity中
                            Intent intent= new Intent();
                            intent.putExtra("username",username.getText().toString());
                            setResult(RESULT_OK,intent);
                            Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                            finish();
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
    public  void initData()
    {

    }
}

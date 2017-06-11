package com.example.liuwei112.Ordering.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuwei112.Ordering.bean.LoginBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private Button btn_reg;
    private Button btn_login;
    private EditText et_username,et_password;


    public void initViews()
    {
        setLayout(R.layout.activity_login);
        btn_reg=(Button)findViewById(R.id.btn_reg);
        btn_login=(Button)findViewById(R.id.btn_login);
        et_username=(EditText)findViewById(R.id.name);
        et_password=(EditText)findViewById(R.id.pass);
    }
    public void initEvents()
    {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();
                intent.setClass(LoginActivity.this,RegActivity.class);
                startActivityForResult(intent,1);

            }

        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取UserModel
                UserModel userModel = new UserModel();
                Call call = userModel.userLogin(et_username.getText().toString(),
                        et_password.getText().toString());
                Callback<LoginBean> callback=new Callback<LoginBean>() {
                    @Override
                    public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                        String userid=response.body().getUserid();

                        if ("0".equals(userid))
                        {
                            //处理失败
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            saveUser(userid);//保存userid
                            //成功intent跳转到mainactivity中
                            Intent intent2= new Intent(LoginActivity.this,MainActivity.class);
                            LoginActivity.this.startActivity(intent2);
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginBean> call, Throwable t) {

                    }
                };
                call.enqueue(callback);


            }
        });

    }
    public void initData()
    {

    }

    public void saveUser(String user_id)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();//获取编辑器
        editor.putString("username",et_username.getText().toString());
        int userid = Integer.parseInt(user_id);
        editor.putInt("user_id",userid);
        editor.putString("userpass",et_password.getText().toString());
        editor.commit();//提交修改
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1&&resultCode==RESULT_OK){
            String username=data.getStringExtra("username");
            et_username.setText(username);
        }

    }



}


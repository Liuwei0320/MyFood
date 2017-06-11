package com.example.liuwei112.Ordering.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.bean.UserBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.liuwei112.myfood.R.id.edit_reg;

public class EditUserActivity extends BaseActivity {
    EditText edit_name,edit_pass,edit_mobilenum,edit_adress;
    Button edit_back,edit_edit;
    String user_name,userpass,mobilenum,adress;

    @Override
    void initViews() {
        setLayout(R.layout.activity_edit_user);
        edit_name=(EditText)findViewById(R.id.edit_name);
        edit_pass=(EditText)findViewById(R.id.edit_pass);
        edit_mobilenum=(EditText)findViewById(R.id.edit_mobilenum);
        edit_adress=(EditText)findViewById(R.id.edit_adress);
        edit_back=(Button)findViewById(R.id.edit_back);
        edit_edit=(Button)findViewById(edit_reg);


    }

    @Override
    void initEvents() {
        edit_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser();
            }
        });
        edit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    void initData() {
            user();

    }
    //用户信息
    private void user()
    {
        UserModel userModel =new UserModel();
        Call call =userModel.getUserById(getUser_id());
        Callback<UserBean> callback =new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean result = response.body();
                edit_name.setText(result.getUsername().toString());
                edit_adress.setText(result.getAddress().toString());
                edit_mobilenum.setText(result.getMobilenum().toString());


            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }
    //修改用户信息
    private void editUser()
    {
        user_name=edit_name.getText().toString();
        userpass=edit_pass.getText().toString();
        mobilenum=edit_mobilenum.getText().toString();
        adress=edit_adress.getText().toString();

        UserModel userModel=new UserModel();
        Call call =userModel.updateUserById(getUser_id(),user_name,userpass,mobilenum,adress);
        Callback<SuccessBean> callback =new Callback<SuccessBean>() {
            @Override
            public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                SuccessBean status=response.body();
                String result =status.getSuccess().toString();
                if (result!="0")
                {
                    Toast.makeText(EditUserActivity.this,"修改成功",Toast.LENGTH_SHORT).show();

                    finish();

                }
                else {
                    Toast.makeText(EditUserActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SuccessBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }
}

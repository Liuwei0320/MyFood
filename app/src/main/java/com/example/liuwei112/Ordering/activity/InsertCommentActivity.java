package com.example.liuwei112.Ordering.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertCommentActivity extends BaseActivity {
    TextView ic_name,ic_time;
    EditText ic_intro;
    Button ic_back,ic_ok;
    int order_id;


    @Override
    void initViews() {
        setLayout(R.layout.activity_insert_comment);
        ic_name = (TextView)findViewById(R.id.ic_name);
        ic_time = (TextView)findViewById(R.id.ic_time);
        ic_intro = (EditText)findViewById(R.id.ic_intro);
        ic_back = (Button)findViewById(R.id.ic_back);
        ic_ok= (Button)findViewById(R.id.ic_ok);

    }

    @Override
    void initEvents() {
        ic_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertComment();
            }
        });
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    void initData() {
        Bundle bundle = getIntent().getExtras();
        order_id =bundle.getInt("order_id");
        ic_name.setText(bundle.getString("food_name"));
        ic_time.setText(bundle.getString("food_time"));
    }
    private void insertComment()
    {
        UserModel userModel = new UserModel();
        Call call = userModel.insertComment(order_id,ic_intro.getText().toString());
        Callback<SuccessBean> callback = new Callback<SuccessBean>() {
            @Override
            public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                SuccessBean result= response.body();
                String status =result.getSuccess();
                if (status == "0")
                {
                    Toast.makeText(InsertCommentActivity.this,"发表失败",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(InsertCommentActivity.this,"发表成功",Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<SuccessBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }

}

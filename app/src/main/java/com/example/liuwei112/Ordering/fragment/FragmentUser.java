package com.example.liuwei112.Ordering.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.liuwei112.Ordering.activity.EditUserActivity;
import com.example.liuwei112.Ordering.activity.UserCommentActivity;
import com.example.liuwei112.Ordering.activity.UserOrderActivity;
import com.example.liuwei112.Ordering.bean.UserBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by songedwin on 2017/3/13.
 */

public class FragmentUser extends Fragment {
    View view=null;
    TextView u_name,u_adress,u_intro,u_tele;
    Button u_comment,u_order,u_edit;
    int user_id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.tab_user, container, false);

        initViews();
        initEvents();
        initdata();
        return view;
    }
    private void initViews()
    {
        u_name=(TextView) view.findViewById(R.id.u_name);
        u_adress=(TextView) view.findViewById(R.id.u_adress);
        u_intro=(TextView) view.findViewById(R.id.u_intro);
        u_tele=(TextView) view.findViewById(R.id.u_tele);
        u_intro=(TextView) view.findViewById(R.id.u_intro);
        u_comment=(Button)view.findViewById(R.id.u_comment);
        u_order=(Button)view.findViewById(R.id.u_order);
        u_edit=(Button)view.findViewById(R.id.u_edit);
    }
    private void initEvents()
    {   //用户信息修改
        u_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), EditUserActivity.class);
                startActivity(intent);
            }
        });

        //查看评论
        u_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), UserCommentActivity.class);
                startActivity(intent);
            }
        });
        //查看订单
        u_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), UserOrderActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initdata()
    {
        UserModel userModel =new UserModel();
        Call call =userModel.getUserById(getUser_id());
        Callback<UserBean> callback =new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean result = response.body();
                u_name.setText(result.getUsername().toString());
                u_adress.setText(result.getAddress().toString());
                u_tele.setText(result.getMobilenum().toString());
                u_intro.setText(result.getComment().toString());

            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }
    public int getUser_id()
    {
        SharedPreferences pref = getActivity().getSharedPreferences("userInfo",MODE_PRIVATE);
        user_id = pref.getInt("user_id",0);//第二个参数为默认值
        return user_id;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

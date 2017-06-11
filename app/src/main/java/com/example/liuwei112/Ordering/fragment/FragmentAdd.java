package com.example.liuwei112.Ordering.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.liuwei112.Ordering.adapter.AddFoodAdapter;
import com.example.liuwei112.Ordering.adapter.AddShopAdapter;
import com.example.liuwei112.Ordering.bean.CollectionBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by songedwin on 2017/3/13.
 */

public class FragmentAdd extends Fragment {
    RadioButton add_food,add_shop;
    RecyclerView recyclerView;
    View view=null;
    private int user_id;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.tab_add, container, false);

        iniViews();
        iniEvents();
        return view;

    }
    public  void iniViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.add_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        SharedPreferences user=getActivity().getSharedPreferences("userInfo",0);
        user_id=user.getInt("user_id",0);

        add_food = (RadioButton) view.findViewById(R.id.add_food);
        add_shop = (RadioButton) view.findViewById(R.id.add_shop);
    }
    public  void iniEvents() {
        add_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_food.setBackgroundResource(R.color.themecolor);
                add_shop.setBackgroundResource(R.color.gray);
                scfood();
            }
        });

        add_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_shop.setBackgroundResource(R.color.themecolor);
                add_food.setBackgroundResource(R.color.gray);
                scshop();
            }
        });

    }
    public  void scshop(){

        UserModel userModel=new UserModel();
        Call call=userModel.getAllUserCollection(user_id,0);
        Callback<List<CollectionBean>> callback =new Callback<List<CollectionBean>>() {
            @Override
            public void onResponse(Call<List<CollectionBean>> call, Response<List<CollectionBean>> response) {
                List<CollectionBean> data = response.body();

                recyclerView.setAdapter(new AddShopAdapter(getActivity(),data));
            }

            @Override
            public void onFailure(Call<List<CollectionBean>> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }

    public  void scfood(){

        UserModel userModel=new UserModel();
        Call call=userModel.getAllUserCollection(user_id,1);
        Callback<List<CollectionBean>> callback =new Callback<List<CollectionBean>>() {
            @Override
            public void onResponse(Call<List<CollectionBean>> call, Response<List<CollectionBean>> response) {
                List<CollectionBean> data = response.body();

                recyclerView.setAdapter(new AddFoodAdapter(getActivity(),data));
            }

            @Override
            public void onFailure(Call<List<CollectionBean>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }


}

package com.example.liuwei112.Ordering.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liuwei112.Ordering.adapter.ShopAdapter;
import com.example.liuwei112.Ordering.bean.ShopBean;
import com.example.liuwei112.Ordering.service.ShopService;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by songedwin on 2017/4/12.
 */
public class FragmentHome extends Fragment {
        RecyclerView recyclerView;


        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // TODO Auto-generated method stub

            View view = inflater.inflate(R.layout.tab_home, container, false);

            recyclerView = (RecyclerView) view.findViewById(R.id.rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局
            getNetData();

            return view;

        }

        private void getNetData() {

                       //获取网络资源
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://60.205.189.39/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            ShopService shopService = retrofit.create(ShopService.class);

            Call<List<ShopBean>> call = shopService.getAllShops();

            call.enqueue(new Callback<List<ShopBean>>() {
                @Override
                public void onResponse(Call<List<ShopBean>> call, Response<List<ShopBean>> response) {
                    List<ShopBean> mDataList = (List<ShopBean>) response.body();
                    recyclerView.setAdapter(new ShopAdapter(getActivity(), mDataList));//设置适配器
                }

                @Override
                public void onFailure(Call<List<ShopBean>> call, Throwable t) {

                }
            });


        }
    }



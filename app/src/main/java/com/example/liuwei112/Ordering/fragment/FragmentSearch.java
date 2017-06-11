package com.example.liuwei112.Ordering.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.liuwei112.Ordering.adapter.ShopContentAdapter;
import com.example.liuwei112.Ordering.bean.ShopContentBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by songedwin on 2017/3/13.
 */

public class FragmentSearch extends Fragment {
    View view=null;
    EditText s_search;
    Button s_button;
    RecyclerView recyclerView;
    String search_intro;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.tab_search, container, false);

        initViews();
        initEvents();
        initdata();
        return view;
    }
    private void initViews()
    {
        s_search=(EditText)view.findViewById(R.id.s_search);
        s_button = (Button)view.findViewById(R.id.s_button);
        recyclerView = (RecyclerView)view.findViewById(R.id.s_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));// 布局

    }
    private void initEvents()
    {
        s_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_intro = s_search.getText().toString();
                initdata();
            }
        });
    }
    private void initdata()
    {
        UserModel userModel =new UserModel();
        Call call = userModel.getFoodBySearch(search_intro);
        Callback<List<ShopContentBean>> callback = new Callback<List<ShopContentBean>>() {
            @Override
            public void onResponse(Call<List<ShopContentBean>> call, Response<List<ShopContentBean>> response) {
                List<ShopContentBean> mDataList =response.body();
                recyclerView.setAdapter(new ShopContentAdapter(getActivity(), mDataList));//设置适配器
            }

            @Override
            public void onFailure(Call<List<ShopContentBean>> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }


}

package com.example.liuwei112.Ordering.service;

import com.example.liuwei112.Ordering.bean.ShopContentBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by songedwin on 2017/4/10.
 */

public interface SearchService {
    //搜索菜谱/口味接口
    @GET("getFoodBySearch.do")
    Call<List<ShopContentBean>> getSearch(
            @Query("search") String search
    );
}

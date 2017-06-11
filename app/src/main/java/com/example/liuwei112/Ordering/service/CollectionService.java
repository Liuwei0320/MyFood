package com.example.liuwei112.Ordering.service;

import com.example.liuwei112.Ordering.bean.CollectionBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by songedwin on 2017/4/10.
 */

public interface CollectionService {
    //获取当前用户的所有收藏信息
    @GET("getAllUserCollection.do")
    Call<List<CollectionBean>> getCollection(
            @Query("user_id") int userid,
            @Query("flag") int flag

    );
}

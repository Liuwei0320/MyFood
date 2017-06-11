package com.example.liuwei112.Ordering.service;


import com.example.liuwei112.Ordering.bean.FoodContentBean;
import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.Ordering.bean.ShopBean;
import com.example.liuwei112.Ordering.bean.ShopContentBean;
import com.example.liuwei112.Ordering.bean.SuccessBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by songedwin on 2017/4/10.
 */

public interface ShopService {
    //获取所有店铺信息接口
    @GET("getAllShops.do")
    Call<List<ShopBean>> getAllShops();

    //获取菜谱详情接口
    @GET("getFoodById.do")
    Call<FoodContentBean> getFoodContent(
            @Query("food_id") int food_id
    );

    //获取当前店铺的所有菜单信息接口
    @GET("getFoodByShop.do")
    Call<List<ShopContentBean>> getShopContent(
            @Query("shop_id") int shop_id
    );

    //获取菜谱评价列表接口
    @GET("getAllUserFoodOrder.do")
    Call<List<OrderBean>> getFoodOrder(
            @Query("food_id") int food_id
    );

    //收藏/取消收藏菜谱接口
    @GET("userCollectFood.do")
    Call<SuccessBean> userCollectFood(
            @Query("user_id") int user_id,
            @Query("food_id") int food_id
    );
    //判断是否收藏接口
    @GET("isCollected.do")
    Call<SuccessBean> isCollected(
            @Query("user_id") int user_id,
            @Query("shop_food_id") int shop_food_id,
            @Query("flag") int flag  //收藏标识：店铺-0；菜谱-1
    );

    //获取店铺详情接口
    @GET("getShopById.do")
    Call<ShopBean> getShopById(
            @Query("shop_id") int shop_id
    );
    //收藏/取消收藏店铺接口
    @GET("userCollectShop.do")
    Call<SuccessBean> userCollectShop(
            @Query("user_id") int user_id,
            @Query("shop_id") int shop_id
    );


}

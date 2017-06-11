package com.example.liuwei112.Ordering.service;

import com.example.liuwei112.Ordering.bean.LoginBean;
import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.bean.UserBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by songedwin on 2017/4/10.
 */

public interface UserService {
    //登录
    @GET("userLogin.do")
    Call<LoginBean> userLogin(
            @Query("username") String username,
            @Query("userpass") String userpass

    );
    //注册
    @GET("userRegister.do")
    Call<SuccessBean> userReg(
            @Query("username") String username,
            @Query("userpass") String userpass,
            @Query("mobilenum") String mobilenum,
            @Query("address") String address,
            @Query("comment") String comment
    );

    //获取用户信息接口
    @GET("getUserById.do")
    Call<UserBean> getUser(
            @Query("user_id") int userid
    );
    //修改用户信息接口
    @GET("updateUserById.do")
    Call<SuccessBean> updateUserById(
            @Query("user_id") int userid,
            @Query("username") String username,
            @Query("userpass") String userpass,
            @Query("mobilenum") String mobilenum,
            @Query("address") String address
            );

    //获取当前用户所有订单信息
    @GET("getAllUserOrder.do")
    Call<List<OrderBean>> getUserOrder(
            @Query("user_id") int userid
    );

    //获取当前用户所有评论信息
    @GET("getAllUserComment.do")
    Call<List<OrderBean>> getComment(
            @Query("user_id") int userid
    );
    //购买菜品
    @GET("insertOrder.do")
    Call<SuccessBean> buyFood(
            @Query("user_id") int user_id,
            @Query("food_id") int food_id,
            @Query("num") int num,
            @Query("sum") double sum,
            @Query("suggesttime") String suggesttime
    );
    //增加评论接口
    @GET("insertComment.do")
    Call<SuccessBean> insertComment(
            @Query("order_id") int order_id,
            @Query("content") String content
    );
    //修改评论接口
    @GET("updateComment.do")
    Call<SuccessBean> updateComment(
            @Query("order_id") int order_id,
            @Query("content") String content
    );
    //删除评论接口
    @GET("deleteComment.do")
    Call<SuccessBean> deleteComment(
            @Query("order_id") int order_id
    );

}


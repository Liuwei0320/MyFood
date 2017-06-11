package com.example.liuwei112.Ordering.model;



import com.example.liuwei112.Ordering.service.CollectionService;
import com.example.liuwei112.Ordering.service.SearchService;
import com.example.liuwei112.Ordering.service.ShopService;
import com.example.liuwei112.Ordering.service.UserService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by songedwin on 2017/4/19.
 */

public class UserModel {
    private Retrofit retrofit;
    public UserModel(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://60.205.189.39/")
                //增加返回值为gson的支持（以实体类返回）
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //登录
    public Call userLogin(String et_username,String et_password)
    {
        UserService userService = retrofit.create(UserService.class);
        return userService.userLogin(et_username,et_password);
    }
    //注册
    public Call userReg(String username,String password,
                        String mobilenum,String address,String comment)
    {
        UserService userService=retrofit.create(UserService.class);
        return  userService.userReg(username,password,mobilenum,address,comment);
    }
    //获取店铺详情；
    public  Call shopContent(int shop_id)
    {
        ShopService shopService=retrofit.create(ShopService.class);
        return  shopService.getShopContent(shop_id);
    }
    //获取用户信息接口
    public  Call getUserById(int user_id)
    {
        UserService userService=retrofit.create(UserService.class);
        return  userService.getUser(user_id);
    }
    //购买接口
    public  Call insertOrder(int user_id,int food_id,int num,double sum,String suggesttime)
    {
        UserService userService=retrofit.create(UserService.class);
        return  userService.buyFood(user_id,food_id,num,sum,suggesttime);
    }
    //获取当前用户的所有收藏信息
    public  Call getAllUserCollection(int user_id,int flag)
    {
        CollectionService collectionService=retrofit.create(CollectionService.class);
        return  collectionService.getCollection(user_id,flag);
    }
    //搜索菜谱/口味接口
    public  Call getFoodBySearch(String search)
    {
        SearchService searchService =retrofit.create(SearchService.class);
        return searchService.getSearch(search);
    }
    //修改用户信息接口
    public Call updateUserById(int user_id,String user_name,String userpass,
                               String mobilenum,String address)
    {
        UserService userService = retrofit.create(UserService.class);
        return userService.updateUserById(user_id,user_name,userpass,mobilenum,address);
    }
    //获取当前用户所有评论信息
    public Call getAllUserComment(int user_id)
    {
        UserService userService = retrofit.create(UserService.class);
        return userService.getComment(user_id);
    }
    //获取当前用户所有订单信息
    public Call getAllUserOrder(int user_id)
    {
        UserService userService = retrofit.create(UserService.class);
        return userService.getUserOrder(user_id);
    }
    //增加评论接口
    public Call insertComment(int order_id,String content)
    {
        UserService userService = retrofit.create(UserService.class);
        return userService.insertComment(order_id,content);
    }
    //修改评论接口
    public Call updateComment(int order_id,String content)
    {
        UserService userService = retrofit.create(UserService.class);
        return userService.updateComment(order_id,content);
    }
    //删除评论信息
    public Call deleteComment(int order_id)
    {
        UserService userService = retrofit.create(UserService.class);
        return userService.deleteComment(order_id);
    }

}



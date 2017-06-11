package com.example.liuwei112.Ordering.model;

import com.example.liuwei112.Ordering.service.ShopService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by songedwin on 2017/5/8.
 */

public class FoodModel {private Retrofit retrofit;
    public FoodModel(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://60.205.189.39/")
                //增加返回值为gson的支持（以实体类返回）
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //判断是否收藏
    public Call isCollected(int user_id, int shop_food_id, int flag)
    {
        ShopService shopService = retrofit.create(ShopService.class);
        return shopService.isCollected(user_id,shop_food_id,flag);
    }

    // 收藏/取消收藏菜谱接口
    public Call userCollectFood(int user_id, int food_id)
    {
        ShopService shopService = retrofit.create(ShopService.class);
        return shopService.userCollectFood(user_id,food_id);
    }
    //收藏/取消收藏店铺接口
    public Call userCollectShop(int user_id, int shop_id)
    {
        ShopService shopService = retrofit.create(ShopService.class);
        return shopService.userCollectShop(user_id,shop_id);
    }

    //获取店铺详情接口
    public Call getShopById(int shop_id)
    {
        ShopService shopService = retrofit.create(ShopService.class);
        return shopService.getShopById(shop_id);
    }
    //获取菜谱详情接口
    public Call getFoodById(int food_id)
    {
        ShopService shopService = retrofit.create(ShopService.class);
        return shopService.getFoodContent(food_id);
    }
    //获取菜谱评价列表接口
    public Call getAllUserFoodOrder(int food_id)
    {
        ShopService shopService = retrofit.create(ShopService.class);
        return shopService.getFoodOrder(food_id);
    }




}



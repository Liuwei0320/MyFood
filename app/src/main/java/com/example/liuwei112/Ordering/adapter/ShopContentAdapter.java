package com.example.liuwei112.Ordering.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuwei112.Ordering.activity.FoodDetailActivity;
import com.example.liuwei112.Ordering.bean.ShopContentBean;
import com.example.liuwei112.myfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by songedwin on 2017/5/3.
 */

public class ShopContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private List mDataList;
    private LayoutInflater mLayoutInflater;
    public ShopContentAdapter(Context mContext, List mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.from(parent.getContext()).inflate(R.layout.shopcardview, parent, false);
        return new ShopContentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ShopContentBean entity = (ShopContentBean) mDataList.get(position);
        if (null == entity)
            return;

        ShopContentAdapter.ViewHolder viewHolder = (ShopContentAdapter.ViewHolder) holder;
        viewHolder.food_name.setText(entity.getFoodname());
        viewHolder.food_intro.setText(entity.getIntro());
        viewHolder.food_price.setText(entity.getPrice()+"元");

        String internetUrl =entity.getPic().toString();
        if(!entity.getPic().isEmpty())
        {
        Picasso
                .with(mContext)
                .load(internetUrl)
                .into(viewHolder.food_pic);}
        else
        {
            viewHolder.food_pic.setImageResource(R.drawable.wu);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, FoodDetailActivity.class);
                intent.putExtra("food_id",entity.getFood_id());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView food_pic;
        TextView food_name,food_intro,food_price;


        public ViewHolder(View itemView) {
            super(itemView);
            food_name = (TextView) itemView.findViewById(R.id.food_title);
            food_pic = (ImageView) itemView.findViewById(R.id.food_pic);
            food_intro = (TextView) itemView.findViewById(R.id.food_intro);
            food_price = (TextView) itemView.findViewById(R.id.food_price);

        }
    }
}


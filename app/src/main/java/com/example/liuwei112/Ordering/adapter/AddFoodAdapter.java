package com.example.liuwei112.Ordering.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuwei112.Ordering.activity.FoodDetailActivity;
import com.example.liuwei112.Ordering.bean.CollectionBean;
import com.example.liuwei112.myfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by songedwin on 2017/5/22.
 */

public class AddFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context mContext;
    private List mDataList;
    private LayoutInflater mLayoutInflater;
    public AddFoodAdapter(Context mContext, List mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.from(parent.getContext()).inflate(R.layout.add_food_cardview, parent, false);
        return new AddFoodAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CollectionBean entity = (CollectionBean) mDataList.get(position);
        if (null == entity)
            return;
        AddFoodAdapter.ViewHolder viewHolder = (AddFoodAdapter.ViewHolder) holder;
        viewHolder.card_title.setText(entity.getFoodname());
        viewHolder.card_keywords.setText(entity.getPrice()+"元");

        String internetUrl =entity.getPic().toString();
        if(!entity.getPic().isEmpty())
        {
            Picasso
                    .with(mContext)
                    .load(internetUrl)
                    .into(viewHolder.card_thumb);}
        else
        {
            viewHolder.card_thumb.setImageResource(R.drawable.wu);
        }
        //取消收藏
        viewHolder.add_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        //进入菜品详情页
        viewHolder.add_button.setOnClickListener(new View.OnClickListener() {
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
        ImageView card_thumb;
        TextView card_title,card_keywords;
        Button add_button;
        ImageButton add_love;

        public ViewHolder(View itemView) {
            super(itemView);
            card_title = (TextView) itemView.findViewById(R.id.addfood_title);
            card_thumb = (ImageView) itemView.findViewById(R.id.addfood_pic);
            add_button =(Button)itemView.findViewById(R.id.addfood_button);
            card_keywords = (TextView) itemView.findViewById(R.id.addfood_price);
            add_love = (ImageButton) itemView.findViewById(R.id.addfood_love);
        }
    }


}

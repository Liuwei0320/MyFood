package com.example.liuwei112.Ordering.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.liuwei112.Ordering.activity.FoodListActivity;
import com.example.liuwei112.Ordering.bean.ShopBean;
import com.example.liuwei112.myfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.liuwei112.myfood.R.id.card_description;

/**
 * Created by songedwin on 2017/3/15.
 */

public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context mContext;
    private List mDataList;
    private LayoutInflater mLayoutInflater;
    public ShopAdapter(Context mContext, List mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ShopBean entity = (ShopBean) mDataList.get(position);
        if (null == entity)
            return;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.card_title.setText(entity.getShopname());
        viewHolder.card_info.setText(entity.getAddress());
        viewHolder.card_keywords.setText(entity.getIntro());


        viewHolder.ratingBar.setRating(entity.getLevel());
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, FoodListActivity.class);
                intent.putExtra("shop_id",entity.getShop_id());
                intent.putExtra("shop_name",entity.getShopname());
                intent.putExtra("shop_tele",entity.getPhonenum());
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
        TextView card_title,card_info,card_keywords,card_id;
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            card_title = (TextView) itemView.findViewById(R.id.card_title);
            card_thumb = (ImageView) itemView.findViewById(R.id.card_thumb);
            card_info = (TextView) itemView.findViewById(R.id.card_info);
            card_keywords = (TextView) itemView.findViewById(R.id.card_keywords);
            card_id= (TextView) itemView.findViewById(card_description);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }
}

package com.example.liuwei112.Ordering.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.liuwei112.Ordering.activity.InsertCommentActivity;
import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.myfood.R;

import java.util.List;

/**
 * Created by songedwin on 2017/3/15.
 */

public class UserOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context mContext;
    private List mDataList;
    private LayoutInflater mLayoutInflater;
    public UserOrderAdapter(Context mContext, List mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.from(parent.getContext()).inflate(R.layout.uo_cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderBean entity = (OrderBean) mDataList.get(position);
        if (null == entity)
            return;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.uo_name.setText(entity.getFoodname());
        viewHolder.uo_time.setText(entity.getOrdertime());
        viewHolder.uo_sum.setText(entity.getSum()+"元");

        viewHolder.uo_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InsertCommentActivity.class);
                intent.putExtra("order_id",entity.getOrder_id());
                intent.putExtra("food_name",entity.getFoodname());
                intent.putExtra("food_time",entity.getOrdertime());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView uo_name,uo_time,uo_sum;
        Button uo_add;

        public ViewHolder(View itemView) {
            super(itemView);

            uo_name = (TextView) itemView.findViewById(R.id.uo_name);
            uo_time = (TextView) itemView.findViewById(R.id.uo_time);
            uo_sum =(TextView)itemView.findViewById(R.id.uo_sum);
            uo_add=(Button)itemView.findViewById(R.id.uo_add);

        }
    }
}

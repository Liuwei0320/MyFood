package com.example.liuwei112.Ordering.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.myfood.R;

import java.util.List;

/**
 * Created by songedwin on 2017/3/15.
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context mContext;
    private List mDataList;
    private LayoutInflater mLayoutInflater;
    public OrderAdapter(Context mContext, List mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.from(parent.getContext()).inflate(R.layout.order_cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderBean entity = (OrderBean) mDataList.get(position);
        if (null == entity)
            return;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.o_name.setText(entity.getUsername());
        viewHolder.o_time.setText(entity.getComment_time());
        viewHolder.o_num.setText(entity.getSum()+"");
        viewHolder.o_intro.setText(entity.getContent());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView o_name,o_time,o_num,o_intro;
        public ViewHolder(View itemView) {
            super(itemView);

            o_name = (TextView) itemView.findViewById(R.id.o_name);
            o_time = (TextView) itemView.findViewById(R.id.o_time);
            o_num = (TextView) itemView.findViewById(R.id.o_num);
            o_intro = (TextView) itemView.findViewById(R.id.o_intro);

        }
    }
}

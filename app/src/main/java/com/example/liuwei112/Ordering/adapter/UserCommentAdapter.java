package com.example.liuwei112.Ordering.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwei112.Ordering.bean.OrderBean;
import com.example.liuwei112.Ordering.bean.SuccessBean;
import com.example.liuwei112.Ordering.model.UserModel;
import com.example.liuwei112.myfood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by songedwin on 2017/3/15.
 */

public class UserCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context mContext;
    private List mDataList;
    private LayoutInflater mLayoutInflater;
    String content;

    public UserCommentAdapter(Context mContext, List mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.from(parent.getContext()).inflate(R.layout.uc_cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final OrderBean entity = (OrderBean) mDataList.get(position);
        if (null == entity)
            return;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.uc_name.setText(entity.getFoodname());
        viewHolder.uc_time.setText(entity.getComment_time());
        viewHolder.uc_comment.setText(entity.getContent());

       final int order_id = entity.getOrder_id();

        //修改评价
        viewHolder.uc_edit.setOnClickListener(new View.OnClickListener() {
            final EditText editText = new EditText(mContext);


            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("请输入")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                content = editText.getText().toString();
                                //修改评论

                                    UserModel userModel = new UserModel();
                                    Call call = userModel.updateComment(order_id,content);
                                    Callback<SuccessBean> callback = new Callback<SuccessBean>() {
                                        @Override
                                        public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                                            SuccessBean result = response.body();
                                            String status = result.getSuccess();
                                            if(status == "0"){
                                                Toast.makeText(mContext,"修改失败",Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<SuccessBean> call, Throwable t) {

                                        }
                                    };
                                    call.enqueue(callback);
                                }

                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        //删除评价
        viewHolder.uc_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel = new UserModel();
                Call call = userModel.deleteComment(order_id);
                Callback<SuccessBean> callback = new Callback<SuccessBean>() {
                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                        SuccessBean result = response.body();
                        String status = result.getSuccess();
                        if(status == "0"){
                            Toast.makeText(mContext,"删除失败",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessBean> call, Throwable t) {

                    }
                };
                call.enqueue(callback);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView uc_name,uc_time,uc_comment,uc_id;
        Button uc_edit;
        ImageButton uc_delete;
        public ViewHolder(View itemView) {
            super(itemView);

            uc_name = (TextView) itemView.findViewById(R.id.uc_name);
            uc_time = (TextView) itemView.findViewById(R.id.uc_time);
            uc_comment =(TextView)itemView.findViewById(R.id.uc_comment);
            uc_edit=(Button)itemView.findViewById(R.id.uc_edit);
            uc_delete=(ImageButton)itemView.findViewById(R.id.uc_delete);
            uc_id =(TextView)itemView.findViewById(R.id.uc_id);

        }
    }


    public void flush(List newDatas){
        mDataList.clear();
        if(newDatas != null){
            mDataList.add(newDatas);
        }
        notifyDataSetChanged();
    }


}

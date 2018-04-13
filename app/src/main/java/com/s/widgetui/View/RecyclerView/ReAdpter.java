package com.s.widgetui.View.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s.widgetui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张垚杰 on 2018/2/11.
 */

public class ReAdpter extends RecyclerView.Adapter<ReAdpter.ViewHolder>{

    private ArrayList<String> mData;

    public ReAdpter(ArrayList<String> data){
        this.mData = data;
    }

    public void updateData(ArrayList<String> data){
        this.mData = data;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        //实例化ViewHolder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定数据
        holder.mtv.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData == null ?
                0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mtv;

        public ViewHolder(View itemView){
            super(itemView);
            mtv = (TextView) itemView.findViewById(R.id.item_tv);
        }

    }
}

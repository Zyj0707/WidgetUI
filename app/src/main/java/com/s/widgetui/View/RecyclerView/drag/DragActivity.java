package com.s.widgetui.View.RecyclerView.drag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s.widgetui.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 张垚杰 on 2018/2/24.
 */

public class DragActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    List<String> str = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_01);
        initView();
        initData();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("DragActivity");
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.my_recycler_view);
        myAdapter = new MyAdapter();
    }

    private void initData(){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL,false));
        for (int i = 0; i < 50; i++) {
            str.add("data: " + i);
        }
        recyclerView.setAdapter(myAdapter);
        new ItemTouchHelper(new MyItemTouchHandler(myAdapter)).attachToRecyclerView(recyclerView);
    }

    private class MyAdapter extends MyItemTouchHandler.ItemTouchAdapterImpl{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag,parent,false)) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView tv = (TextView) holder.itemView.findViewById(R.id.text1);
            tv.setText(str.get(position));
        }

        @Override
        public int getItemCount() {
            return str.size();
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {
            // 拖动排序的回调,这里交换集合中数据的位置
            Collections.swap(str, fromPosition, toPosition);
        }

        @Override
        public void onItemRemove(int position) {
            // 滑动删除的回调,这里删除指定的数据
        }

        @Override
        protected boolean autoOpenSwipe() {
            return false;
        }
    }
}

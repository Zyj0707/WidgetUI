package com.s.widgetui.View.RecyclerView.drag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s.widgetui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张垚杰 on 2018/2/24.
 */

public class SwipeActivity extends AppCompatActivity{

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 11; i++) {
            str.add("data: " + i);
        }
        recyclerView.setAdapter(myAdapter);
        new ItemTouchHelper(new MyItemTouchHandler(myAdapter)).attachToRecyclerView(recyclerView);
    }

    private class MyAdapter extends MyItemTouchHandler.ItemTouchAdapterImpl{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe,parent,false)) {
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

        }

        @Override
        public void onItemRemove(int position) {
            str.remove(position);
        }

        @Override
        protected boolean autoOpenDrag() {
            return false;
        }
    }
}

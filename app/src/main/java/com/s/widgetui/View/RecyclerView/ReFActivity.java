package com.s.widgetui.View.RecyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.s.widgetui.R;

import java.util.ArrayList;

/**
 * Created by 张垚杰 on 2018/2/11.
 */

public class ReFActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private Toolbar toolbar;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_01);
        initData();
        initView();
    }

    private void initData(){
        //设置横向或者竖向
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new ReAdpter(getData());
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("RecyclerView");
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置间隔
       // mRecyclerView.addItemDecoration(new ReDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new SimplePaddingDecoration(this, LinearLayoutManager.VERTICAL));
        //mRecyclerView.addItemDecoration(new SimplePaddingDecoration(this, LinearLayoutManager.HORIZONTAL));
        //设置标签
        mRecyclerView.addItemDecoration(new LeftAndRightTagDecoration(this, LinearLayoutManager.VERTICAL));
        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i< 20; i++){
            data.add(i + temp);
        }
        return data;
    }
}

package com.s.widgetui.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.s.widgetui.R;

import java.util.List;

/**
 * Created by 张垚杰 on 2018/2/10.
 */

public class CoordinatorLayout1 extends AppCompatActivity{
    RecyclerView mRecyclerView;
    List<String> mDatas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinatorlayout_01);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("toolBar");
        setSupportActionBar(toolbar);
    }
}

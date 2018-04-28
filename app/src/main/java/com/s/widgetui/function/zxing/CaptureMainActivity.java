package com.s.widgetui.function.zxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.s.widgetui.MainActivity;
import com.s.widgetui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 张垚杰 on 2018/3/5.
 */

public class CaptureMainActivity extends AppCompatActivity {

    @BindView(R.id.tv_message)
    TextView textView;
    @BindView(R.id.toolbar_title)
    TextView toolbarT;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_main);
        ButterKnife.bind(CaptureMainActivity.this);
        toolbarT.setText("ZXING");
        //toolbar.setTitle("ZXING");
       // textView.setText("123");
    }

    @OnClick(R.id.bt_send)
    public void send(){
        Intent intent = new Intent();
        intent.setClass(CaptureMainActivity.this, CaptureActivity.class);
        startActivityForResult(intent , 1);
        Toast.makeText(this,"onclick",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(resultCode == Activity.RESULT_OK && data != null){
                    //isBleopen = true;
                    String code = data.getStringExtra("code");
                    if (code != null) {
                        textView.setText(code);
                    }
                }
        }
    }


}

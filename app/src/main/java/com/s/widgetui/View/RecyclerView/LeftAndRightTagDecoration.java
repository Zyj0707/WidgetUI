package com.s.widgetui.View.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.s.widgetui.R;

/**
 * Created by 张垚杰 on 2018/2/11.
 */

public class LeftAndRightTagDecoration extends RecyclerView.ItemDecoration{

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private int tagWidth;
    private Paint leftPaint;
    private Paint rightPaint;

    private int mOrientation;

    public LeftAndRightTagDecoration(Context context, int orientation){
        leftPaint = new Paint();
        leftPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        rightPaint = new Paint();
        rightPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        setOrientation(orientation);
        tagWidth = 20;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if(mOrientation == VERTICAL_LIST){
            drawVertical(c, parent);
        }else {
            drawHorizontal(c, parent);
        }
    }

    private void setOrientation(int orientation){
        if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    public void drawVertical(Canvas c, RecyclerView parent){
        int childCount = parent.getChildCount();
        for(int i = 0; i< childCount; i++){
            View view = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(view);
            boolean isLeft = pos % 2 == 0;
            if(isLeft){
                float left = view.getLeft();
                float right = left + tagWidth;
                float top = view.getTop();
                float bottom = view.getBottom();
                c.drawRect(left, top, right, bottom, leftPaint);
            }else{
                float right = view.getRight();
                float left = right - tagWidth;
                float top = view.getTop();
                float bottom = view.getBottom();
                c.drawRect(left, top, right, bottom, rightPaint);
            }

        }

    }

    public void drawHorizontal(Canvas c, RecyclerView parent){

    }
}

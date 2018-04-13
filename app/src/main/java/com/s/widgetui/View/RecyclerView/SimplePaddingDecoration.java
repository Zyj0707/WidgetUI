package com.s.widgetui.View.RecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.s.widgetui.R;

/**
 * Created by 张垚杰 on 2018/2/11.
 */

public class SimplePaddingDecoration extends RecyclerView.ItemDecoration{

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private int dividerHeight;
    private Paint dividerPaint;

    private int mOrientation;

    public SimplePaddingDecoration(Context context,  int orientation){
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        setOrientation(orientation);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(mOrientation == VERTICAL_LIST) {
            outRect.bottom = 1;
        }else{
            //outRect.right = 1;
            outRect.set(0, 0, 1, 0);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
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

    private void drawVertical(Canvas c, RecyclerView parent){
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + 1;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }

    }

    private void drawHorizontal(Canvas c, RecyclerView parent){
        int childCount = parent.getChildCount();
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        for(int i = 0; i < childCount; i++){
            View view = parent.getChildAt(i);
            float left = view.getRight();
            float right = view.getRight() + 1;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }

    }
}

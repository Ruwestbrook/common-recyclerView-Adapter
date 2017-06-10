package com.example.westbrook.recyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by westbrook on 2017/6/6.
 */

public class MyDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "MyDecoration";

    public MyDecoration(int top) {
        mPaint = new Paint();
        this.top=top;
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }
    private int top;
    private Paint mPaint;
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Log.d(TAG, "getItemOffsets: "+parent.getChildCount());
        int position=parent.getChildAdapterPosition(view);
        if(position!=0){
            //Rect代表一个长方形
            outRect.top=top;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //left top right bottom 表示这个矩形的左上角的坐标是(left,top),右下角的坐标是(right,bottom);
        Rect rect=new Rect();
        int count=parent.getChildCount();
        //从0和从1开始没有区别，因为从0开始的话，其getTop的值是0，及矩形的右下角的坐标是0
        //没有大小，
        for (int i =0; i < count; i++) {
            //左上x坐标
            rect.left = parent.getPaddingLeft();
            //右下x坐标
            rect.right = parent.getWidth() - parent.getPaddingRight();
            //左上y坐标
            rect.top=parent.getChildAt(i).getTop()-this.top;
            //右下y坐标
            rect.bottom=parent.getChildAt(i).getTop();
            c.drawRect(rect,mPaint);
        }
    }
}

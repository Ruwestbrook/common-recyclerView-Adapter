package com.example.westbrook.recyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by westbrook on 2017/6/6.
 */

public class LinearDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDrawable;
    private Context mContext;

    public LinearDecoration( Context context,int drawableId) {
        mContext = context;
        mDrawable = ContextCompat.getDrawable(mContext,drawableId);
    }

    @Override

    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //left top right bottom 表示这个矩形的左上角的坐标是(left,top),右下角的坐标是(right,bottom);
        Rect rect = new Rect();
        int count = parent.getChildCount();
        //从0和从1开始没有区别，因为从0开始的话，其getTop的值是0，及矩形的右下角的坐标是0
        //没有大小，
        for (int i = 0; i < count; i++) {
            //左上x坐标
            rect.left = parent.getPaddingLeft();
            //右下x坐标
            rect.right = parent.getWidth() - parent.getPaddingRight();
            //左上y坐标
            rect.top = parent.getChildAt(i).getTop() - mDrawable.getIntrinsicHeight();
            //右下y坐标
            rect.bottom = parent.getChildAt(i).getTop();
            mDrawable.setBounds(rect);
            mDrawable.draw(c);
        }
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position=parent.getChildAdapterPosition(view);
        if(position!=0){
            outRect.top=mDrawable.getIntrinsicHeight();
        }
    }
}

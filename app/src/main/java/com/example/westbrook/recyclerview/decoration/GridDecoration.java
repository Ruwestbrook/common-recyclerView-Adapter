package com.example.westbrook.recyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by westbrook on 2017/6/6.
 */

public class GridDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "GridDecoration";
    private Drawable mDrawable;
    private Context mContext;
    private int number;

    public GridDecoration(Context context, int drawableId) {
        mContext = context;
        mDrawable= ContextCompat.getDrawable(mContext,drawableId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager manager= (GridLayoutManager) parent.getLayoutManager();
       number=manager.getSpanCount();
        for (int i = 0; i < parent.getChildCount(); i++) {
            int index=i;
            if(index/number==0){
                index=0;
            }
            Rect rect=new Rect();
            rect.left = parent.getPaddingLeft();
            rect.right = parent.getWidth() - parent.getPaddingRight();
            rect.top = parent.getChildAt(index).getTop() - mDrawable.getIntrinsicHeight();
            rect.bottom = parent.getChildAt(index).getTop();
            mDrawable.setBounds(rect);
            mDrawable.draw(c);

               if((i+1)%number!=0){
                   //getRight 获取其距离父视图的右边的位置 从左上角开始计算
                   int left =parent.getChildAt(i).getRight();
                   //getTop 获取其距离父视图上边的位置
                   int top = parent.getChildAt(i).getTop();
                   int right=left+mDrawable.getIntrinsicWidth();
                   int bottom=parent.getChildAt(i).getBottom();
                   mDrawable.setBounds(left,top,right,bottom);
                   mDrawable.draw(c);
               }



        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager manager= (GridLayoutManager) parent.getLayoutManager();
        number=manager.getSpanCount();
       //outRect表示在ItemView外围的一个长方形
        //其值代表了ItemView的偏移量
       int position=parent.getChildAdapterPosition(view);
        if(position/number!=0){
            outRect.top=mDrawable.getIntrinsicHeight();
        }
        if((position+1)%number!=0){
            outRect.right=mDrawable.getIntrinsicWidth();
        }
    }
}

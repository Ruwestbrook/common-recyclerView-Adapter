package com.example.westbrook.recyclerview.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.westbrook.recyclerview.R;

/**
 * Created by westbrook on 2017/6/9.
 */

public class MyScroll extends RecyclerView.OnScrollListener {
    private static final String TAG = "MyScroll";
    private LinearLayoutManager mLinearLayout;
    private Context mContext;
    private int position;
    private int allCount;
    private boolean isNext=false;
    private int newCount=20;
    public MyScroll(LinearLayoutManager linearLayout,Context context,int count) {
        mLinearLayout = linearLayout;
        mContext=context;
        allCount=count;
    }

    @Override

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(isNext && newState==0){

            Toast.makeText(mContext, "加载数据", Toast.LENGTH_SHORT).show();
//            MyAdapter adapter= (MyAdapter) recyclerView.getAdapter();
//            if(newCount+20<allCount){
//                newCount+=20;
//            }else {
//                newCount=allCount;
//            }
//            adapter.setAllCount(newCount);
//            adapter.notifyDataSetChanged();
            isNext=false;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if(dy>0){
            //下拉刷新，向上滚动
            MyAdapter adapter= (MyAdapter) recyclerView.getAdapter();
            position=adapter.getItemCount();
            if((position-1)<=mLinearLayout.findLastVisibleItemPosition()){
                recyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
                isNext=true;
            }
           Log.d("TEST","isNext"+isNext);


        }
    }
            }

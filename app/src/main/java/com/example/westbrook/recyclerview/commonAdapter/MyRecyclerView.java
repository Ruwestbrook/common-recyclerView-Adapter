package com.example.westbrook.recyclerview.commonAdapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by westbrook on 2017/6/10.
 */

public class MyRecyclerView extends RecyclerView {

   CommonAdapter adapter;
     HeaderAdapter mAdapter;
    private List<View> mHeaderViews;
    private List<View> mFooterViews;
    public MyRecyclerView(Context context) {
        this(context,null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHeaderViews=new ArrayList<>();
        mFooterViews=new ArrayList<>();
    }

    public void addHeaderView(View view) {
        mHeaderViews.add(view);
        if(adapter==null)
        adapter= (CommonAdapter) getAdapter();
        if(mAdapter==null)
            mAdapter=new HeaderAdapter(adapter);
        if(adapter!=null){
//            mAdapter=new HeaderAdapter(adapter);
//            for (View headerView : mHeaderViews) {
//                mAdapter.addHeaderFooterView(1,headerView);
//            }
                mAdapter.addHeaderFooterView(1,view);
            this.setAdapter(mAdapter);
        }
    }
    public void addFooterView(View view) {
        if(adapter==null)
            adapter= (CommonAdapter) getAdapter();
        if(mAdapter==null)
            mAdapter=new HeaderAdapter(adapter);
        if(adapter!=null){
            mAdapter.addHeaderFooterView(-1,view);
            this.setAdapter(mAdapter);
        }
    }
    public void removeHeaderView(View view) {
        if(adapter==null)
            adapter= (CommonAdapter) getAdapter();
        if(mAdapter==null)
            mAdapter=new HeaderAdapter(adapter);
        if(adapter!=null){
            mAdapter.removeHeaderFooterView(1,view);
            mAdapter.notifyDataSetChanged();
            this.setAdapter(mAdapter);
        }
    }
    public void removeFooterView(View view) {
        if(adapter==null)
            adapter= (CommonAdapter) getAdapter();
        if(mAdapter==null)
            mAdapter=new HeaderAdapter(adapter);
        if(adapter!=null){
            mAdapter.removeHeaderFooterView(-1,view);
            mAdapter.notifyDataSetChanged();
            this.setAdapter(mAdapter);
        }
    }
}

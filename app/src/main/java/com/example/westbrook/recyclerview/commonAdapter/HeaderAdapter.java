package com.example.westbrook.recyclerview.commonAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by westbrook on 2017/6/10.
 */

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HeaderAdapter";
    private static final int HEADERNUMBER = -10000000;
    private static  int HEADER = -10000000;
    private static final int FOOTERNUMBER = -20000000;
    private static  int FOOTER = -20000000;
    private SparseArray<View> headerView= new SparseArray<>();
    private SparseArray<View> footView= new SparseArray<>();
    private int count;
    public HeaderAdapter(CommonAdapter adapter) {
        mAdapter = adapter;
    }
    CommonAdapter mAdapter ;
    public void addHeaderFooterView(int key,View view){
        if(key>0){
            //头部传入1
            headerView.put(++HEADER,view);
        }else {
            //底部传入-1
            footView.put(++FOOTER,view);
        }

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType<0 && viewType>HEADERNUMBER){
            //头部
            Log.d(TAG, "onCreateViewHolder: ");
           return createHeaderFooterViewHolder( headerView.get(viewType));
        }else if(viewType<HEADERNUMBER && viewType>FOOTERNUMBER){
               return createHeaderFooterViewHolder(footView.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent,viewType);
    }

    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position<headerView.size()){
            return;
        }
        if(position>count){
            return;
        }
            mAdapter.onBindViewHolder((ViewHolder) holder,position-headerView.size());
    }

    @Override
    public int getItemViewType(int position) {
        if(position<headerView.size()){
            Log.d(TAG, "getItemViewType: "+position);
            return headerView.keyAt(position);
        }
        if(position>count){
            return footView.keyAt(position-count-1);
        }
        return mAdapter.getItemViewType(position-headerView.size());
    }

    @Override
    public int getItemCount() {
        count=mAdapter.getItemCount()+headerView.size()-1;
        return mAdapter.getItemCount()+headerView.size()+footView.size();
    }

    public void removeHeaderFooterView(int i, View view) {
        if(i>0){
            //删除头部
            headerView.removeAt(headerView.indexOfValue(view));
        }else {
            //删除底部
        }
    }
}

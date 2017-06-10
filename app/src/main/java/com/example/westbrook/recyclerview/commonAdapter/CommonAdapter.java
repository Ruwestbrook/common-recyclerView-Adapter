package com.example.westbrook.recyclerview.commonAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by westbrook on 2017/6/9.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "CommonAdapter";
    private List<T> mData;
    private Context mContext;
    private int LayoutId;
    //点击事件
    private OnItemClick mItemClick;
    //长按事件
    protected OnLongItemClick mLongClick;
    private LayoutInflater mInflater;
    //多条目
    private MultiTypeSupport supportType;
    public CommonAdapter(List<T> data, Context context, int layoutId) {
        mData = data;
        mInflater=LayoutInflater.from(context);
        mContext = context;
        LayoutId = layoutId;
    }
    public CommonAdapter(List<T> data, Context context,MultiTypeSupport supportType) {
       this(data,context,-1);
        this.supportType=supportType;

    }
    public void setOnclickItemListener(OnItemClick mListener) {
        mItemClick=mListener;
    }
    public void setOnLongClickListener(OnLongItemClick click){
        mLongClick=click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建布局
        if(supportType!=null){
            //需要  多布局
            LayoutId=viewType;
        }
        View view;
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
        , ViewGroup.LayoutParams.WRAP_CONTENT);

        view= mInflater.inflate(LayoutId,parent,false);
        view.setLayoutParams(lp);
        Log.d(TAG, "onCreateViewHolder: "+view.toString());
        return new ViewHolder(view,mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if(supportType!=null){
           return supportType.getLayoutId(mData.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(mItemClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClick.onclick(position);
                }
            });
        }
        if(mLongClick!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClick.onclick(position);
                    return true;
                }
            });
        }
        filling(holder,mData.get(position),position);
    }

    public abstract void filling(ViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
       // return 2;
       return mData==null?0:mData.size();
    }
}

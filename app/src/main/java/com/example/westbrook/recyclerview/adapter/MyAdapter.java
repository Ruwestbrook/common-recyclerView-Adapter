package com.example.westbrook.recyclerview.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.westbrook.recyclerview.R;
import com.example.westbrook.recyclerview.entity.MyMessage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by westbrook on 2017/6/7.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final String TAG = "MyAdapter";
    private Context mContext;
    private List<MyMessage.DataBean.CategoriesBean.CategoryListBean> mList;
    private onClickItem mClick;
    public MyAdapter(Context context, List<MyMessage.DataBean.CategoriesBean.CategoryListBean> list) {
        mContext = context;
        mList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.channel_list_item, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MyMessage.DataBean.CategoriesBean.CategoryListBean bean=mList.get(position);
        Glide.with(mContext).load(Uri.parse(bean.getIcon_url())).
                    into(holder.mChannelIcon);
        holder.mChannelText.setText(bean.getName());
        holder.mChannelTopic.setText(bean.getIntro());
     String info="订阅"+bean.getSubscribe_count()+"|总帖数<font color='#FF678D'>"+
             bean.getTotal_updates()+"<font>";
        holder.mChannelUpdateInfo.setText(Html.fromHtml(info));
        if(mClick!=null){
           holder.view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mClick.onClick(position);
               }
           });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnclickItemListener(onClickItem mListener) {
        mClick=mListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @Bind(R.id.channel_icon)//左侧图标
        ImageView mChannelIcon;
        @Bind(R.id.channel_text)//左上方字
        TextView mChannelText;
        @Bind(R.id.channel_topic)//左中
        TextView mChannelTopic;
        @Bind(R.id.channel_update_info)//坐下
        TextView mChannelUpdateInfo;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            mChannelIcon= (ImageView) itemView.findViewById(R.id.channel_icon);
            mChannelText=(TextView)itemView.findViewById(R.id.channel_text);
            mChannelTopic=(TextView)itemView.findViewById(R.id.channel_topic);
            mChannelUpdateInfo=(TextView)itemView.findViewById(R.id.channel_update_info);
        }
    }
    //使用接口 1.需要重写这个“类”中的方法
    public interface onClickItem{
        void onClick(int position);
    }

}

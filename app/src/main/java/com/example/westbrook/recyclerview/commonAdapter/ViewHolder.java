package com.example.westbrook.recyclerview.commonAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by westbrook on 2017/6/9.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ViewHolder";
    private SparseArray<View> mArray;
    private Context mContext;

    public ViewHolder(View itemView,Context context) {
        super(itemView);
        Log.d(TAG, "ViewHolder: "+itemView.getId());
        mContext=context;
     mArray=new SparseArray<>();
    }
     //通过泛型返回View
    public  <T extends View>T getView(int id){
        View view=mArray.get(id);
        if(view==null){
            view=itemView.findViewById(id);
            mArray.put(id,view);
        }
        return (T) view;
    }
    //为TextView设置内容
    public ViewHolder setText(int id,CharSequence text){
        TextView textView=getView(id);
        textView.setText(text);
        return this;
    }
    public ViewHolder setImage(int id,String path){
        ImageView imageView=getView(id);
        Glide.with(mContext).load(Uri.parse(path)).
                into(imageView);
        return this;
    }
    public ViewHolder setImage(int id, Bitmap bitmap){
        ImageView imageView=getView(id);
        Glide.with(mContext).load(bitmap).
                into(imageView);
        return this;
    }
    public ViewHolder setImage(int id, int imgId){
        ImageView imageView=getView(id);
        Glide.with(mContext).load(imgId).
                into(imageView);
        return this;
    }
    public ViewHolder setListener(int id,View.OnClickListener listener){
        View view=getView(id);
        view.setOnClickListener(listener);
        return this;
    }
}

package com.example.westbrook.recyclerview.list;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westbrook.recyclerview.R;
import com.example.westbrook.recyclerview.adapter.MyAdapter;
import com.example.westbrook.recyclerview.commonAdapter.CommonAdapter;
import com.example.westbrook.recyclerview.commonAdapter.OnItemClick;
import com.example.westbrook.recyclerview.commonAdapter.ViewHolder;
import com.example.westbrook.recyclerview.entity.MyMessage;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";
 public static final String URL="http://is.snssdk.com/2/essay/discovery/v3/?iid=6152551759&channel=360&aid=7&\n" +
         "app_name=joke_essay&ac=wifi&device_id=30036118478&device_brand=Xiaomi&update_version_code=5701&manifest_version_code=570&\n" +
         "longitude=113.000366&latitude=28.171377&device_platform=android&device_type=Redmi+Note+3&version_name=5.7.0";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mLayout;
    private int allCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(URL).build();
                try {
                    Response response=client.newCall(request).execute();
                    Message message=mHandler.obtainMessage();
                    message.what=1;
                    Bundle bundle=new Bundle();
                    bundle.putString("data",response.body().string());
                    message.setData(bundle);
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    CommonAdapter<MyMessage.DataBean.CategoriesBean.CategoryListBean> mViewAdapter;
    private MyAdapter myAdapter;
    private LinearLayoutManager manager;
    private int newCount=20;
    private List<MyMessage.DataBean.CategoriesBean.CategoryListBean> mList;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Gson gson=new Gson();
                   String data=msg.getData().getString("data");
                   MyMessage m=gson.fromJson(data,MyMessage.class);
                    allCount=m.getData().getCategories().getCategory_list().size();
                    mList=m.getData().getCategories().getCategory_list();
                    myAdapter=new MyAdapter(ListActivity.this,mList);
                mViewAdapter= new CommonAdapter<MyMessage.DataBean.CategoriesBean.CategoryListBean>
                            (mList,ListActivity.this,R.layout.channel_list_item){

                        @Override
                        public void filling(ViewHolder holder, MyMessage.DataBean.CategoriesBean.CategoryListBean bean, int position) {
                            String info="订阅"+bean.getSubscribe_count()+"|总帖数<font color='#FF678D'>"+
                                    bean.getTotal_updates()+"<font>";
                          holder.setText(R.id.channel_text,bean.getName())
                                  .setText(R.id.channel_topic,bean.getIntro())
                                  .setText(R.id.channel_update_info,Html.fromHtml(info))
                                  .setImage(R.id.channel_icon,bean.getIcon_url());

                        }
                    };
                    mViewAdapter.setOnclickItemListener(new OnItemClick() {
                        @Override
                        public void onclick(int position) {
                            Toast.makeText(ListActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    manager=new LinearLayoutManager(ListActivity.this);
                    mRecyclerView.setAdapter(mViewAdapter);
                    mRecyclerView.setLayoutManager(manager);

                    
            }
        }
    };
}

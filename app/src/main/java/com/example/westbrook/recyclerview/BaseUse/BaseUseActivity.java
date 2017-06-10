package com.example.westbrook.recyclerview.BaseUse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.westbrook.recyclerview.R;
import com.example.westbrook.recyclerview.decoration.GridDecoration;
import com.example.westbrook.recyclerview.decoration.LinearDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseUseActivity extends AppCompatActivity {
    MyAdapter mAdapter;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private RecyclerView.ItemDecoration mLinearDecoration;
    private RecyclerView.ItemDecoration mGridDecotration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_use);
        ButterKnife.bind(this);
        setTitle("RecyclerView的使用");
       mAdapter= new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        //LinearLayoutManager线性布局
      //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //GridLayoutManager 每行四个
      //  mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //添加分割线
       //mRecyclerView.addItemDecoration(new LinearDecoration(this,R.drawable.linear_item));
        mLinearDecoration=new LinearDecoration(this,R.drawable.linear_item);
        mGridDecotration= new GridDecoration(this,R.drawable.linear_item);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,5));
        mRecyclerView.addItemDecoration(mGridDecotration);
    }
    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView mTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                mTextView= (TextView) itemView.findViewById(R.id.text);
            }
        }
        //创建ViewHolder
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder;
            View view= LayoutInflater.from(BaseUseActivity.this).
                        inflate(R.layout.item,parent,false);
                viewHolder=new ViewHolder(view);
            return viewHolder;
        }
                //绑定数据
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
         holder.mTextView.setText("哈哈哈哈哈");
        }
            //数据数量
        @Override
        public int getItemCount() {
            return 10;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.one:
                mRecyclerView.removeItemDecoration(mGridDecotration);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.addItemDecoration(mLinearDecoration);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.two:
                mRecyclerView.removeItemDecoration(mLinearDecoration);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
                mRecyclerView.addItemDecoration(mGridDecotration);
                mAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

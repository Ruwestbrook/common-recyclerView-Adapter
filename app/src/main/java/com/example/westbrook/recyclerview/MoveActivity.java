package com.example.westbrook.recyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.westbrook.recyclerview.commonAdapter.CommonAdapter;
import com.example.westbrook.recyclerview.commonAdapter.ViewHolder;
import com.example.westbrook.recyclerview.decoration.GridDecoration;
import com.example.westbrook.recyclerview.decoration.LinearDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoveActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    List<String> mList;
    CommonAdapter<String> mAdapter;
    GridDecoration mDecoration;
    LinearDecoration mLinearDecoration;
    private static final String TAG = "MoveActivity";
    ItemTouchHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        ButterKnife.bind(this);
        getData();
        mDecoration=new GridDecoration(this,R.drawable.linear_item);
        mLinearDecoration=new LinearDecoration(this,R.drawable.linear_item);
        mAdapter=new CommonAdapter<String>(mList,this,R.layout.move_item) {
            @Override
            public void filling(ViewHolder holder, String s, int position) {
                holder.setText(R.id.move,mList.get(position));
            }
        };
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.setAdapter(mAdapter);
        mHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                // 如果你不想上下拖动，可以将 dragFlags = 0
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

                // 如果你不想左右滑动，可以将 swipeFlags = 0
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

                //最终的动作标识（flags）必须要用makeMovementFlags()方法生成
                int flags = makeMovementFlags(dragFlags, swipeFlags);
                return flags;
            }
            //状态改变
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                //actionState 有三种状态 int: One of
                // ACTION_STATE_IDLE, 没用动作
                // ACTION_STATE_SWIPE  滑动
                // ACTION_STATE_DRAG. 拖动
                if(actionState!=ItemTouchHelper.ACTION_STATE_IDLE)
                    viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#661e1e1e"));
                ViewCompat.setTranslationX(viewHolder.itemView,0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                ///得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                mAdapter.notifyItemMoved(fromPosition,toPosition);
                if(fromPosition<toPosition){
                    for (int i = fromPosition; i <toPosition ; i++) {
                        //自己的数据交换数据
                        Collections.swap(mList,i,i+1);
                    }
                }else {
                    for (int i = toPosition; i <fromPosition ; i++) {
                        //自己的数据交换数据
                        Collections.swap(mList,i,i+1);
                    }
                }

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //删除后的操作
                //获取删除的itemview的位置
                int position=viewHolder.getAdapterPosition();
                //删除数据
                mList.remove(position);
                //动画效果
                mAdapter.notifyItemRemoved(position);

            }
        });
        mHelper.attachToRecyclerView(mRecyclerView);
    }

    public void getData() {
        mList=new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            char a= (char) (65+i);
            mList.add(String.valueOf(a));
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
                mRecyclerView.removeItemDecoration(mDecoration);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.addItemDecoration(mLinearDecoration);
                break;
            case R.id.two:
                mRecyclerView.removeItemDecoration(mLinearDecoration);
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
                mRecyclerView.addItemDecoration(mDecoration);
        }
        return super.onOptionsItemSelected(item);
    }
}

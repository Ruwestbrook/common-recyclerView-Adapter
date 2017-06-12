package com.example.westbrook.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.westbrook.recyclerview.commonAdapter.CommonAdapter;
import com.example.westbrook.recyclerview.commonAdapter.MultiTypeSupport;
import com.example.westbrook.recyclerview.commonAdapter.MyRecyclerView;
import com.example.westbrook.recyclerview.commonAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdapterActivity extends AppCompatActivity {


    @Bind(R.id.recycler_view)
    MyRecyclerView mRecyclerView;
    List<Person> mList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        ButterKnife.bind(this);
        getData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<Person> commonAdapter=new CommonAdapter<Person>(mList,this,R.layout.chat_me) {
            @Override
            public void filling(ViewHolder holder, Person o, int position) {
                holder.setText(R.id.chat_me,o.getChat());

            }
        };
        mRecyclerView.setAdapter(commonAdapter);
        View view= LayoutInflater.from(this).inflate(R.layout.header,mRecyclerView,false);
        mRecyclerView.addHeaderView(view);
        View view1= LayoutInflater.from(this).inflate(R.layout.header,mRecyclerView,false);
        mRecyclerView.addFooterView(view1);
        mRecyclerView.removeHeaderView(view);

    }

    private void getData(){
        for (int i = 0; i < 50; i++) {
            Person person=new Person();
            if(i%2==0){
                person.setChat("哈哈哈哈");
                person.setIsMe(1);
                mList.add(person);
            }else {
                person.setChat("呵呵呵呵");
                person.setIsMe(0);
                mList.add(person);
            }
        }
    }
    class Person{
        public String getChat() {
            return chat;
        }

        public void setChat(String chat) {
            this.chat = chat;
        }

        public int getIsMe() {
            return isMe;
        }

        public void setIsMe(int isMe) {
            this.isMe = isMe;
        }

        public String chat;
        public int isMe;
    }

}

package com.example.westbrook.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.westbrook.recyclerview.commonAdapter.CommonAdapter;
import com.example.westbrook.recyclerview.commonAdapter.MultiTypeSupport;
import com.example.westbrook.recyclerview.commonAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdapterActivity extends AppCompatActivity {
    private static final String TAG = "AdapterActivity";

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    List<Person> mList=new ArrayList<>();
    private MultiTypeSupport<Person> myType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        ButterKnife.bind(this);
        getData();
        myType=new MultiTypeSupport<Person>() {
            @Override
            public int getLayoutId(Person item) {
                if(item.getIsMe()==0){
                    return R.layout.chat_friend;
                }else {
                    return R.layout.chat_me;
                }
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<Person> commonAdapter=new CommonAdapter<Person>(mList,this,myType) {
            @Override
            public void filling(ViewHolder holder, Person o, int position) {
                if(o.getIsMe()==0){
                    holder.setText(R.id.chat,o.getChat());
                    Log.d(TAG, "filling: "+ holder.itemView.toString());
                }else {
                    holder.setText(R.id.chat_me,o.getChat());
                    Log.d(TAG, "filling: "+ holder.itemView.toString());
                }
            }
        };
        mRecyclerView.setAdapter(commonAdapter);
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
    class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

        }

        class Holder extends RecyclerView.ViewHolder{

            public Holder(View itemView) {
                super(itemView);
            }
        }
    }
}
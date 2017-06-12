package com.example.westbrook.recyclerview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.westbrook.recyclerview.BaseUse.BaseUseActivity;
import com.example.westbrook.recyclerview.list.ListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Bind(R.id.textView)
    Button mTextView;
    @Bind(R.id.button)
    Button mButton;
    @Bind(R.id.button2)
    Button mButton2;
    @Bind(R.id.button3)
    Button mButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    private long exportTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exportTime)>3000){
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exportTime=System.currentTimeMillis();

            }else {
                finish();
                System.exit(0);
            }
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.textView, R.id.button,R.id.button2,R.id.button3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView:
                startActivity(new Intent(this, BaseUseActivity.class));
                break;
            case R.id.button:
              startActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, AdapterActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, MoveActivity.class));
                break;
        }
    }

}

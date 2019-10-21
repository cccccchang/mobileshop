package com.cc.mobileshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdActivity extends AppCompatActivity {
    @BindView(R.id.iv_count)
    TextView tv_acount;

    Thread thread;

    // boolean isStop=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        //绑定activi
        ButterKnife.bind(this);

        tv_acount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdActivity.this,MainActivity.class));
                /*if(thread!=null) {
                    thread.stop();
                }*/
                //isStop=true;
                finish();
            }
        });

        thread =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=5;i>=0;i--){
                    /*if(isStop){
                        return;
                    }*/
                    SystemClock.sleep(1000);
                    final int count=i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_acount.setText("点击跳转 "+count);
                        }
                    });
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(AdActivity.this,MainActivity.class));
                        finish();
                    }
                });

            }
        });
        thread.start();
    }

}

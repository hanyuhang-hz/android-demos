package com.hyh.randomservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    BinderService binderService;
    // 文本框组件ID
    int[] tvid = {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5,
            R.id.textView6, R.id.textView7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_random = (Button) findViewById(R.id.btn);

        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取BinderService类中的随机数数组
                List number = binderService.getRandomNumber();
                for (int i = 0; i < number.size(); i++) {
                    TextView tv = (TextView) findViewById(tvid[i]);
                    String strNumber = number.get(i).toString();
                    tv.setTextColor(Color.GRAY);
                    tv.setText(strNumber);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        // 设置启动Activity时与后台Service进行绑定
        super.onStart();
        Intent intent = new Intent(this, BinderService.class);
        // 绑定指定Service
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        // 设置关闭Activity时解除与后台Service的绑定
        super.onStop();
        unbindService(conn);
    }

    // 设置与后台Service进行通讯
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取后台Service
            binderService = ((BinderService.MyBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
}
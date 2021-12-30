package com.hyh.progressbar;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    public final static String TAG = "ProgressBar";

    final int TIME = 20;    //定义时间长度

    final int TIMER_MSG = 0x001;    //定义消息代码

    private ProgressBar timer;    //声明水平进度条

    private int mProgressStatus = 0;    //定义完成进度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (ProgressBar) findViewById(R.id.timer);      //获取进度条组件
        handler.sendEmptyMessage(TIMER_MSG);    //发送消息，启动进度条
        Log.d(TAG, "onCreate tid:" + android.os.Process.myTid());
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //当前进度大于0
            if (TIME - mProgressStatus > 0) {
                mProgressStatus++;         //进度+1
                Log.d(TAG, "handleMessage mProgressStatus:" + mProgressStatus);
                Log.d(TAG, "handleMessage tid:" + android.os.Process.myTid());
                timer.setProgress(TIME - mProgressStatus);            //更新进度条的显示进度
                handler.sendEmptyMessageDelayed(TIMER_MSG, 1000);    //延迟一秒发送消息
            } else {
                Toast.makeText(MainActivity.this, "时间到！", Toast.LENGTH_SHORT).show();         //提示时间已到
                Log.d(TAG, "handleMessage timeout!");
            }
        }
    };
}
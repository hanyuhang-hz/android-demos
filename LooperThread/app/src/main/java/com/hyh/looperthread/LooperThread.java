package com.hyh.looperthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {
    public Handler handler;
    @Override
    public void run() {
        super.run();
        Looper.prepare();					//初始化Looper对象
        //实例化一个Handler对象
        handler = new Handler() {
            public void handleMessage(Message msg) {
                Log.i("Looper", "handle msg:" + String.valueOf(msg.what));
                Log.d("Looper", "handleMessage tid:" + android.os.Process.myTid());
            }
        };

        Message m=handler.obtainMessage();	//获取一个消息
        m.what=0x7;						//设置Message的what属性的值
        handler.sendMessage(m);			//发送消息
        Log.i("Looper", "send msg:" + String.valueOf(m.what));
        Log.d("Looper", "sendMessage tid:" + android.os.Process.myTid());
        Looper.loop();						//启动Looper
    }
}

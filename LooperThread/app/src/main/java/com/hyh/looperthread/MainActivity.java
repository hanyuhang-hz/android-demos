package com.hyh.looperthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Looper", "onCreate tid:" + android.os.Process.myTid());
        LooperThread thread=new LooperThread();		//创建一个线程
        thread.start();								//开启线程
    }
}
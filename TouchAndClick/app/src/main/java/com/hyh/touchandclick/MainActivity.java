package com.hyh.touchandclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity","onClick 单击事件");
            }
        });
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    Log.i("MainActivity","onTouch 按下");
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    Log.i("MainActivity","onTouch 抬起");
                }
                return false;	// 表示未消耗掉这个事件
                //return true;	// 表示消耗掉这个事件
            }
        });
    }
}
package com.hyh.headactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HeadActivity.class);
                // 启动intent对应的Activity
                startActivityForResult(intent, 0x11);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断是否为待处理的结果
        if(requestCode==0x11 && resultCode==0x11){
            // 获取传递的数据包
            Bundle bundle=data.getExtras();
            // 获取选择的头像ID
            int imageId=bundle.getInt("imageId");
            ImageView iv=(ImageView)findViewById(R.id.imageView);
            // 显示选择的头像
            iv.setImageResource(imageId);
        }
    }
}
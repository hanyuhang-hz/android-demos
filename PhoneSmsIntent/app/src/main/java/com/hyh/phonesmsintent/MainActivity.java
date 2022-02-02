package com.hyh.phonesmsintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton_phone);
        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton_sms);
        imageButton.setOnClickListener(l);
        imageButton1.setOnClickListener(l);
    }

    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.imageButton_phone:
                    intent.setAction(intent.ACTION_DIAL); //调用拨号面板
                    intent.setData(Uri.parse("tel:18768193385")); //设置要拨打的号码
                    //intent.setData(Uri.parse("tel:17800928376")); //设置要拨打的号码
                    startActivity(intent);
                    break;
                case R.id.imageButton_sms:
                    intent.setAction(intent.ACTION_SENDTO); //调用发送短信息
                    intent.setData(Uri.parse("smsto:17800928376")); //设置要发送的号码
                    intent.putExtra("sms_body", "Welcome to Android!"); //设置要发送的信息内容
                    startActivity(intent);
            }
        }
    };
}
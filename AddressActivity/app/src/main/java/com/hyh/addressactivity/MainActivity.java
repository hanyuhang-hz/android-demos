package com.hyh.addressactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {  
            @Override

            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.et_name)).getText().toString();
                String phone = ((EditText) findViewById(R.id.et_phone)).getText().toString();
                String site = ((EditText) findViewById(R.id.et_site)).getText().toString();
                if (!"".equals(site)&& !"".equals(name) && !"".equals(phone)) {
                    // 将输入的信息保存到Bundle中，通过Intent传递到另一个Activity当中并显示出来
                    Intent intent = new Intent(MainActivity.this, AddressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("name", name);
                    bundle.putCharSequence("phone", phone);
                    bundle.putCharSequence("site", site);
                    intent.putExtras(bundle);
                    // 启动Activity
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"请将收货地址填写完整！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
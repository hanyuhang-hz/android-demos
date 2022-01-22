package com.hyh.addressactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddressActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Intent intent = getIntent();
        // 获取传递的Bundle信息
        Bundle bundle = intent.getExtras();
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(bundle.getString("name"));
        TextView phone = (TextView) findViewById(R.id.phone);
        phone.setText(bundle.getString("phone"));
        TextView site = (TextView) findViewById(R.id.site);
        site.setText(bundle.getString("site"));
    }
}

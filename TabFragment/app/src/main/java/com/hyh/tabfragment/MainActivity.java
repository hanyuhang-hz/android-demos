package com.hyh.tabfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        imageView1.setOnClickListener(l);
        imageView2.setOnClickListener(l);
    }
    View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            // 开启一个事务
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = null;
            switch (v.getId()) {
                case R.id.image1:
                    f = new Fragment1();
                    break;
                case R.id.image2:
                    f = new Fragment2();
                    break;
                default:
                    break;
            }
            // 替换Fragment
            ft.replace(R.id.fragment, f);
            // 提交事务
            ft.commit();
        }
    };
}
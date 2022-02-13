package com.hyh.scrollview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout, linearLayout2;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.ll);
        linearLayout2 = new LinearLayout(MainActivity.this);
        // 设置为纵向排列
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        scrollView = new ScrollView(MainActivity.this);
        // 默认布局中添加滚动视图组件
        linearLayout.addView(scrollView);
        // 滚动视图组件中添加新建布局
        scrollView.addView(linearLayout2);
        TextView textView = new TextView(MainActivity.this);
        textView.setText(R.string.cidian);
        // 新建布局中添加TextView组件
        linearLayout2.addView(textView);
    }
}
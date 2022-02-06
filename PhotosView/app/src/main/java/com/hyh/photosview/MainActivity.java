package com.hyh.photosview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private  ImageView[] img=new ImageView[12];
    private int[] imagePath=new int[]{
            R.mipmap.img01,R.mipmap.img02,R.mipmap.img03,R.mipmap.img04,
            R.mipmap.img05,R.mipmap.img06,R.mipmap.img07,R.mipmap.img08,
            R.mipmap.img09,R.mipmap.img10,R.mipmap.img11,R.mipmap.img12
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout layout=(GridLayout)findViewById(R.id.layout);
        for(int i=0;i<imagePath.length;i++){
            img[i]=new ImageView(MainActivity.this);
            img[i].setImageResource(imagePath[i]);
            img[i].setPadding(2, 2,2, 2);
            // 设置图片的宽度和高度
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(232,136);
            // 为ImageView组件设置布局参数
            img[i].setLayoutParams(params);
            // 将ImageView组件添加到布局管理器中
            layout.addView(img[i]);
        }
    }
}
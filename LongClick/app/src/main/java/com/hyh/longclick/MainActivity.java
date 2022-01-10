package com.hyh.longclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.view.ContextMenu;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                registerForContextMenu(v); // 将长按事件注册菜单中
                openContextMenu(v); // 打开菜单
                // 表示用户消耗此事件，现象为收藏菜单显示后一直存在
                return true;
                // 表示系统消耗此事件，现象为收藏菜单显示后立刻消失
                // return false;
            }
        });
    }
    // 创建菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("收藏");
    }
}
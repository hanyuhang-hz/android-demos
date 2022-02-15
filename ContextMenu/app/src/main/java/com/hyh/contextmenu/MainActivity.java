package com.hyh.contextmenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView introduce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        introduce = (TextView) findViewById(R.id.introduce);
        // 为文本框注册上下文菜单
        registerForContextMenu(introduce);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.introduce_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_copy:
                Toast.makeText(MainActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_collect:
                Toast.makeText(MainActivity.this,"已收藏",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
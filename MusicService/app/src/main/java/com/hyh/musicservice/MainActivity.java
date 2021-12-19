package com.hyh.musicservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton btn_play = (ImageButton) findViewById(R.id.btn_play);

        // 启动服务与停止服务，实现播放背景音乐与停止播放背景音乐
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicService.isplay == false) {
                    // 启动服务，从而实现播放背景音乐
                    startService(new Intent(MainActivity.this, MusicService.class));
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.play, null));
                } else {
                    // 停止服务，从而实现停止播放背景音乐
                    stopService(new Intent(MainActivity.this, MusicService.class));
                    ((ImageButton) v).setImageDrawable(getResources().getDrawable(R.drawable.stop, null));
                }
            }
        });
    }

    @Override
    protected void onStart() {
        // 进入界面时，启动背景音乐服务
        startService(new Intent(MainActivity.this, MusicService.class));
        super.onStart();
    }
}
package com.hyh.imageswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {
    private int[] arrayPictures = new int[]{R.mipmap.img01, R.mipmap.img02, R.mipmap.img03,
            R.mipmap.img04, R.mipmap.img05, R.mipmap.img06,
            R.mipmap.img07, R.mipmap.img08, R.mipmap.img09,
    };
    private ImageSwitcher imageSwitcher;
    private int pictutureIndex;
    // 手指按下的X坐标
    private float touchDownX;
    // 手指松开的X坐标
    private float touchUpX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(arrayPictures[pictutureIndex]);
                return imageView;
            }
        });
        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 取得手指按下的X坐标
                    touchDownX = event.getX();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // 取得手指松开的X坐标
                    touchUpX = event.getX();

                    if (touchUpX - touchDownX > 100) {  // 从左往右，看下一张
                        pictutureIndex = pictutureIndex == 0 ? arrayPictures.length - 1 : pictutureIndex - 1;
                        // 设置图片切换的动画
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_left));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_right));
                        // 设置当前要看的图片
                        imageSwitcher.setImageResource(arrayPictures[pictutureIndex]);

                    } else if (touchDownX - touchUpX > 100)  // 从右往左，看上一张
                    {
                        pictutureIndex = pictutureIndex == arrayPictures.length - 1 ? 0 : pictutureIndex + 1;
                        // 设置切换动画
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_left));
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right));
                        // 设置要看的图片
                        imageSwitcher.setImageResource(arrayPictures[pictutureIndex]);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
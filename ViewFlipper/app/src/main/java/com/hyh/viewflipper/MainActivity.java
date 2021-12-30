package com.hyh.viewflipper;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class MainActivity extends Activity {
    final int FLAG_MSG = 0x001;
    private ViewFlipper flipper;
    private Message message;
    //定义图片数组
    private int[] images = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8};
    private Animation[] animation = new Animation[2];  //定义动画数组，为ViewFlipper指定切换动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);  //将遍历的图片保存在ImageView中
            flipper.addView(imageView);             //加载图片
        }

        animation[0] = AnimationUtils.loadAnimation(this, R.anim.slide_in_right); //右侧平移进入动画
        animation[1] = AnimationUtils.loadAnimation(this, R.anim.slide_out_left); //左侧平移退出动画
        flipper.setInAnimation(animation[0]);   //为flipper设置图片进入动画效果
        flipper.setOutAnimation(animation[1]);  //为flipper设置图片退出动画效果

        message=Message.obtain();
        message.what=FLAG_MSG;
        handler.sendMessage(message);
    }

    Handler handler = new Handler() {  //创建android.os.Handler对象
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == FLAG_MSG) {
                flipper.showPrevious();
            }
            message=handler.obtainMessage(FLAG_MSG);   //获取要发送的消息
            handler.sendMessageDelayed(message, 3000);
        }
    };
}
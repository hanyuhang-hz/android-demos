package com.hyh.randomservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinderService extends Service {
    public BinderService() {
    }
    // 创建MyBinder内部类并获取服务对象与Service状态
    public class MyBinder extends Binder {
        public BinderService getService() {  //创建获取Service的方法
            return BinderService.this;
        }
    }

    // 返回MyBinder服务对象
    @Override
    public IBinder onBind(Intent intent) {  // 必须实现的绑定方法
        return new MyBinder();
    }

    // 创建获取随机号码的方法
    public List getRandomNumber() {
        List resArr = new ArrayList();
        String strNumber="";
        // 将随机获取的数字转换为字符串添加到ArrayList数组中
        for (int i = 0; i < 7; i++) {
            int number = new Random().nextInt(33) + 1;
            //把生成的随机数格式化为两位的字符串
            if (number<10) {
                strNumber = "0" + String.valueOf(number);
            } else {
                strNumber = String.valueOf(number);
            }
            resArr.add(strNumber);
        }
        return resArr;
    }


    @Override
    public void onDestroy() {  //销毁该Service
        super.onDestroy();
    }
}

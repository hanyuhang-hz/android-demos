package com.hyh.movehat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class HatView extends View {
    public float bitmapX; // 帽子显示位置的X坐标
    public float bitmapY; // 帽子显示位置的Y坐标

    public HatView(Context context) {
        super(context);
        bitmapX = 30;
        bitmapY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.hat);
        Log.d("HatView", "bitmapX: " + bitmapX + " bitmapY: " + bitmapY);
        // 绘制帽子
        canvas.drawBitmap(bitmap, bitmapX, bitmapY, paint);
        if (bitmap.isRecycled()) {
            bitmap.recycle(); // 强制回收图片
        }
    }
}

package com.hyh.camerapreview;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {
    public final static String TAG = "CameraPreview";
    private Camera camera;
    private boolean isPreview = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.CAMERA}, 1);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        // 设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 判断手机是否安装SD卡
        if (!android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "请安装SD卡！", Toast.LENGTH_SHORT).show();
        }

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        final SurfaceHolder surfaceHolder = surfaceView.getHolder();
        // 设置该SurfaceHolder自己不维护缓冲
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        ImageButton preview = (ImageButton) findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果相机为非预览模式，则打开相机
                if (!isPreview) {
                    camera = Camera.open();
                    isPreview = true;
                }
                try {
                    // 设置用于显示预览的SurfaceView
                    camera.setPreviewDisplay(surfaceHolder);

                    Camera.Parameters parameters = camera.getParameters();
                    // 测试Camera.Parameters
                    // (1)setPictureFormat======================================
                    // PixelFormat.JPEG:jpeg
                    // parameters.setPictureFormat(PixelFormat.JPEG);     // 指定图片为JPEG图片
                    // parameters.set("jpeg-quality", 80);                // 设置图片的质量
                    // PixelFormat.YCbCr_420_SP:NV21,等价于ImageFormat.NV21
                    parameters.setPictureFormat(PixelFormat.YCbCr_420_SP);
                    // (2)setWhiteBalance======================================
                    // auto
                    String whiteBalance = parameters.getWhiteBalance();
                    Log.d(TAG, "whiteBalance:" + whiteBalance);
                    // WHITE_BALANCE_INCANDESCENT:偏蓝
                    // WHITE_BALANCE_FLUORESCENT:偏淡蓝
                    // WHITE_BALANCE_WARM_FLUORESCENT:偏淡蓝
                    // WHITE_BALANCE_DAYLIGHT:日光，正常
                    // WHITE_BALANCE_CLOUDY_DAYLIGHT:阴天，偏黄
                    // WHITE_BALANCE_TWILIGHT:暮光，偏黄
                    // WHITE_BALANCE_SHADE:偏黄
                    parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_DAYLIGHT);
                    whiteBalance = parameters.getWhiteBalance();
                    Log.d(TAG, "whiteBalance:" + whiteBalance);
                    // (3)setExposureCompensation======================================曝光补偿
                    int minEx = parameters.getMinExposureCompensation();
                    int maxEx = parameters.getMaxExposureCompensation();
                    float stepEx = parameters.getExposureCompensationStep();
                    Log.d(TAG, "minEx:" + minEx + " maxEx:" + maxEx + " stepEx:" + stepEx);
                    parameters.setExposureCompensation(6);
                    // (4)setAutoExposureLock======================================
                    boolean isAELS = parameters.isAutoExposureLockSupported();
                    Log.d(TAG, "isAES:" + isAELS);
                    boolean getAELS = parameters.getAutoExposureLock();
                    Log.d(TAG, "getAES:" + getAELS);
                    parameters.setAutoExposureLock(false);
                    getAELS = parameters.getAutoExposureLock();
                    Log.d(TAG, "getAES:" + getAELS);
                    // (5)setAutoWhiteBalanceLock======================================
                    boolean isAWBLS = parameters.isAutoWhiteBalanceLockSupported();
                    Log.d(TAG, "isAWBLS:" + isAWBLS);
                    boolean getAWBL = parameters.getAutoWhiteBalanceLock();
                    Log.d(TAG, "getAWBL:" + getAWBL);
                    parameters.setAutoWhiteBalanceLock(false);
                    getAWBL = parameters.getAutoWhiteBalanceLock();
                    Log.d(TAG, "getAWBL:" + getAWBL);
                    // (5)setFocusMode======================================
                    String fm = parameters.getFocusMode();
                    Log.d(TAG, "fm:" + fm);
                    // FOCUS_MODE_AUTO:聚焦
                    // FOCUS_MODE_INFINITY:不聚焦
                    // FOCUS_MODE_MACRO:聚焦
                    // FOCUS_MODE_CONTINUOUS_VIDEO:跟随预览的物体远近自动聚焦，afcb返回fail
                    // FOCUS_MODE_CONTINUOUS_PICTURE:跟随预览的物体远近自动聚焦，afcb返回fail
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                    fm = parameters.getFocusMode();
                    Log.d(TAG, "fm:" + fm);
                    camera.setParameters(parameters);

                    camera.startPreview();
                    // AF:自动对焦
                    camera.autoFocus(afcb);
                    camera.setDisplayOrientation(90);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ImageButton takePicture = (ImageButton) findViewById(R.id.takephoto);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {
                    camera.takePicture(null, null, jpeg);
                }
            }
        });
    }

    final Camera.AutoFocusCallback afcb = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if(success) {
                // success表示对焦成功
                Log.d(TAG, "afcb: success");
                //camera.setOneShotPreviewCallback(null);
            }
            else {
                // 未对焦成功
                Log.d(TAG, "afcb: fail");
            }
        }
    };

    // 照片回调函数，实现将照片保存到系统图库中
    final Camera.PictureCallback jpeg = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 根据拍照所得的数据创建位图
            final Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            camera.stopPreview();
            isPreview = false;
            Log.d(TAG, "Environment.getExternalStorageDirectory():" + Environment.getExternalStorageDirectory());
            //获取sd卡根目录
            File appDir = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/");
            if (!appDir.exists()) {      // 如果该目录不存在就创建该目录
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
            // 保存拍到的图片
            try {
                FileOutputStream fos = new FileOutputStream(file);  // 创建一个文件输出流对象
                boolean ret = bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);  // 将图片内容压缩为JPEG格式输出到输出流对象中
                Log.d(TAG, "bm.compress ret:" + ret);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //将照片插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(MainActivity.this.getContentResolver(),
                        file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // 最后通知图库更新
            MainActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + "")));
            Toast.makeText(MainActivity.this, "照片保存至：" + file, Toast.LENGTH_LONG).show();
            resetCamera();
        }
    };

    // 创建resetCamera()方法，实现重新预览功能
    private void resetCamera() {
        if (!isPreview) {
            camera.startPreview();
            isPreview = true;
        }
    }

    @Override
    protected void onPause() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
        }
        super.onPause();
    }
}
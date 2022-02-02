package com.hyh.camerarecord;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class MainActivity extends Activity {
    public final static String TAG = "CameraRecord";

    private ImageButton play, stop, record;
    private MediaRecorder mediaRecorder;
    private SurfaceView surfaceView;
    private boolean isRecord = false;
    private File videoDir;
    private android.hardware.Camera camera;
    private File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.RECORD_AUDIO}, 1);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.CAMERA}, 1);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        // 设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (!android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            Toast.makeText(MainActivity.this, "请安装SD卡！", Toast.LENGTH_SHORT).show();
        }

        record = (ImageButton) findViewById(R.id.record);
        stop = (ImageButton) findViewById(R.id.stop);
        play = (ImageButton) findViewById(R.id.play);
        stop.setEnabled(false);
        play.setEnabled(false);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceView.getHolder().setFixedSize(1920, 1080);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecord) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    record.setEnabled(true);
                    stop.setEnabled(false);
                    play.setEnabled(true);
                    Toast.makeText(MainActivity.this, "录像保存在：" + path, Toast.LENGTH_SHORT).show();
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过Intent跳转播放视频界面
                Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
                startActivity(intent);
            }
        });

    }

    // 创建record()方法，实现录制功能
    private void record() {
        // 设置录制视频保存的文件夹
        videoDir = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera/");
        if (!videoDir.exists()) {
            videoDir.mkdir();
        }
        String fileName = "video.mp4";
        path = new File(videoDir, fileName);

        mediaRecorder = new MediaRecorder();

        Camera.Parameters parameters = camera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        camera.setParameters(parameters);
        camera.startPreview();
        camera.setDisplayOrientation(90);
        camera.unlock();
        mediaRecorder.setCamera(camera);
        mediaRecorder.reset();   //重置MediaRecorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);           // 设置麦克风获取声音
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);        // 设置摄像头获取图像
        mediaRecorder.setVideoEncodingBitRate(1920 * 1080);                    // 设置清晰度

        CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        mediaRecorder.setProfile(profile);
        mediaRecorder.setVideoSize(profile.videoFrameWidth, profile.videoFrameHeight);

        mediaRecorder.setOutputFile(path.getAbsolutePath());                   // 设置视频输出路径
        mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface()); // 设置使用SurfaceView预览视频
        mediaRecorder.setOrientationHint(90);                                  // 调整播放视频角度
        try {
            mediaRecorder.prepare();                                           // 准备录像
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaRecorder.start();                                                 // 开始录制
        Toast.makeText(MainActivity.this, "开始录像", Toast.LENGTH_SHORT).show();
        record.setEnabled(false);
        stop.setEnabled(true);
        play.setEnabled(false);
        isRecord = true;
    }

    @Override
    protected void onResume() {
        camera = android.hardware.Camera.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        camera.stopPreview();
        camera.release();
        super.onPause();
    }
}
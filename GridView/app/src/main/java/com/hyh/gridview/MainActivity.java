package com.hyh.gridview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private Integer[] picture = {R.mipmap.img01, R.mipmap.img02, R.mipmap.img03,
            R.mipmap.img04, R.mipmap.img05, R.mipmap.img06, R.mipmap.img07,
            R.mipmap.img08, R.mipmap.img09, R.mipmap.img10, R.mipmap.img11,
            R.mipmap.img12,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView= (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

    }
    public class ImageAdapter extends BaseAdapter{
        private Context mContext;
        public ImageAdapter(Context c){
            mContext=c;
        }
        @Override
        public int getCount() {
            return picture.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView==null){
                imageView=new ImageView(mContext);
                // 为组件设置宽高
                imageView.setLayoutParams(new GridView.LayoutParams(300, 176));
                // 选择图片铺设方式
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            }else{
                imageView= (ImageView) convertView;
            }
            imageView.setImageResource(picture[position]);
            return imageView;
        }
    }
}
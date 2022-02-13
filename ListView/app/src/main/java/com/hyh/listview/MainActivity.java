package com.hyh.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = (ListView) findViewById(R.id.listview);
        int[] imageId = new int[]{R.mipmap.img01, R.mipmap.img02, R.mipmap.img03,
                R.mipmap.img04, R.mipmap.img05, R.mipmap.img06,
                R.mipmap.img07, R.mipmap.img08, R.mipmap.img09,
        };
        String[] title = new String[]{"刘一", "陈二", "张三", "李四", "王五",
                "赵六", "孙七", "周八", "吴九"};
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imageId[i]);
            map.put("名字", title[i]);
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.main, new String[] { "名字", "image" }, new int[] {
                R.id.title, R.id.image });
        // 将适配器与ListView关联
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,map.get("名字").toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
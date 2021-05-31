package com.example.flowlayoutmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.flowlayoutlibrary.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FlowLayout flowLayout;
    private MyFlowAdapter myFlowAdapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowLayout = findViewById(R.id.fl);
        myFlowAdapter = new MyFlowAdapter();
        flowLayout.setAdapter(myFlowAdapter);
        list.add("北京");
        list.add("北京天安门");
        list.add("我爱大天津");
        list.add("啦啦啦");
        list.add("你好啊");
        list.add("Hello Lucy");
        list.add("吃遍天下");
        list.add("舌尖上的中国");
        list.add("门前大桥下");
        list.add("窗前明月光");
        list.add("白月光与朱砂痣");
        list.add("李白");
        list.add("I am fine");
        list.add("啦啦啦啦啦啦啦啦啦啦");
        list.add("就这样吧");
        myFlowAdapter.update(list);
    }
}

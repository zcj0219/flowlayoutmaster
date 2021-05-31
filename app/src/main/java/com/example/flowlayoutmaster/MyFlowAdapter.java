package com.example.flowlayoutmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.flowlayoutlibrary.FlowAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFlowAdapter extends FlowAdapter {
    List<String> data = new ArrayList<>();

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        FrameLayout fl = (FrameLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_flow, parent, false);
        TextView txt = fl.findViewById(R.id.txt);
        txt.setText(data.get(position));
        return fl;
    }

    public void update(List<String> list){
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }
}

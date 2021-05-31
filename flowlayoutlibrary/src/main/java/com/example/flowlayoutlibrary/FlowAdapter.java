package com.example.flowlayoutlibrary;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class FlowAdapter {
    private List<FlowObserver> observers = new ArrayList<>();
    public abstract int getCount();

    public abstract View getView(int position, ViewGroup parent);

    public void attach(FlowObserver observer){
        observers.add(observer);
    }

    public void notifyDataSetChanged(){
        for (FlowObserver observer : observers) {
            observer.update();
        }
    }
}

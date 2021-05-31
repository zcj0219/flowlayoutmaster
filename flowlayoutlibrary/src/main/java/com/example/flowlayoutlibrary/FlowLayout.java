package com.example.flowlayoutlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class FlowLayout extends ViewGroup implements FlowObserver{
    private int mLineSpacing;
    private int mItemSpacing;
    private FlowAdapter mAdapter;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        if(a != null){
            mLineSpacing = a.getDimensionPixelOffset(R.styleable.FlowLayout_line_spacing, 10);
            mItemSpacing = a.getDimensionPixelOffset(R.styleable.FlowLayout_item_spacing, 10);
            a.recycle();
        }else {
            mLineSpacing = 10;
            mItemSpacing = 10;
        }
    }

    public void setAdapter(FlowAdapter adapter){
        if(adapter == null){
            throw new NullPointerException("adapter不能为空");
        }
        this.mAdapter = adapter;
        mAdapter.attach(this);
        removeAllViews();
        int count = mAdapter.getCount();
        for(int i = 0;i<count;i++){
            View view = mAdapter.getView(i, this);
            addView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int lineWidth = 0;
        int lineHeight = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (lineWidth + childWidth > sizeWidth) {
                lineWidth = childWidth + mItemSpacing;
                lineHeight = lineHeight + childHeight + mLineSpacing;
            } else {
                lineWidth = lineWidth + childWidth + mItemSpacing;
            }
            if(i == count - 1){
                lineHeight = lineHeight + childHeight;
            }
        }
        setMeasuredDimension(sizeWidth, lineHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++){
            View child  = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (left + childWidth > getWidth()) {
                left = 0;
                top = top + childHeight + mLineSpacing;
                child.layout(left, top, left + childWidth + mItemSpacing, top + childHeight);
                left = left + childWidth + mItemSpacing;
            } else {
                child.layout(left, top, left + childWidth + mItemSpacing, top + childHeight);
                left = left + childWidth + mItemSpacing;
            }
        }
    }

    @Override
    public void update() {
        removeAllViews();
        int count = mAdapter.getCount();
        for(int i = 0;i<count;i++){
            View view = mAdapter.getView(i, this);
            addView(view);
        }
    }
}

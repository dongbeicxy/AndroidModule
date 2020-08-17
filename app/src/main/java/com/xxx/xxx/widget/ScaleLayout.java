package com.xxx.xxx.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.xxx.xxx.R;

import java.lang.ref.WeakReference;

/**
 *  * 宽长(按比例) 布局
 *  * @email aman_yin@163.com
 *  * @author Aman
 */
public class ScaleLayout extends RelativeLayout {
    private float heightToWidth;
    //布局 宽度与屏幕宽度 的比例值
    private float widthSize;
    private WeakReference<Context> wr;

    public ScaleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        wr = new WeakReference<Context>(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScaleLayout);
        widthSize = a.getFloat(R.styleable.ScaleLayout_widthToScreen, 1.0f);
        //宽长默认1:1
        heightToWidth = a.getFloat(R.styleable.ScaleLayout_heightToWidth, 1.0f);
        a.recycle();
    }

    public ScaleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        wr = new WeakReference<Context>(context);
    }

    public ScaleLayout(Context context) {
        this(context, null);
        wr = new WeakReference<Context>(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //这个方法决定了当前View的大小
        setMeasuredDimension((int) (getDefaultSize(0, widthMeasureSpec) * widthSize), (int) (getDefaultSize(0, widthMeasureSpec) * widthSize * heightToWidth));
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}


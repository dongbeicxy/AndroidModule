package com.xxx.xxx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 标题栏 布局 封装
 * @创建时间 :2020/4/27 14:03
 */
public class TitleLayout extends RelativeLayout {

    private ImageView returnIcon;
    private TextView title;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_layout, this);
    }
}

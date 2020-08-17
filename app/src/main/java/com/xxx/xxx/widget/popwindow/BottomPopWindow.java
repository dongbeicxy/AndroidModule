package com.xxx.xxx.widget.popwindow;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 底部PopWindow
 * @创建时间 :2020/4/14 13:21
 */
public class BottomPopWindow extends PopupWindow {
    private LayoutInflater layoutInflater;
    private View popView;
    private Activity activity;

    public BottomPopWindow(Activity activity) {
        this.layoutInflater = LayoutInflater.from(activity);
        this.activity = activity;
        init();
    }

    private void init() {
        popView = layoutInflater.inflate(R.layout.bottom_pop_window, null);
        //把View添加到PopWindow中
        this.setContentView(popView);
        //设置弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置弹出窗体的高
        //this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        this.setHeight(height * 1 / 2);
        //设置弹出窗体可点击
        this.setFocusable(true);
        //设置弹出窗体动画效果
        this.setAnimationStyle(R.style.BottomPopWindowAnimation);

        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//实例化一个ColorDrawable颜色为透明

        //实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置弹出窗体的背景
        //this.setBackgroundDrawable(dw);

//        popView.setOnTouchListener(new View.OnTouchListener() {//设置背景区域外为点击消失popwindow
//            public boolean onTouch(View v, MotionEvent event) {
//                int height = popView.findViewById(R.id.pop_layout).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
//                return true;
//            }
//        });
    }

}

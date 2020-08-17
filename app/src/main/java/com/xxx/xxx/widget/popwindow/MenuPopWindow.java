package com.xxx.xxx.widget.popwindow;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.xxx.xxx.R;
import com.xxx.xxx.widget.TriangleView;

/**
 * @作者 xzb
 * @描述: 三角形 菜单 PopWindow
 * @创建时间 :2020/4/14 13:21
 */
public class MenuPopWindow extends PopupWindow {
    private LayoutInflater layoutInflater;
    private View popView;
    private Activity activity;

    public MenuPopWindow(Activity activity) {
        this.layoutInflater = LayoutInflater.from(activity);
        this.activity = activity;
        init();
    }

    private void init() {
        popView = layoutInflater.inflate(R.layout.menu_pop_window, null);
        //把View添加到PopWindow中
        this.setContentView(popView);
        //设置弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置弹出窗体可点击
        this.setFocusable(true);
        //设置弹出窗体动画效果
        //this.setAnimationStyle(R.style.MenuPopWindowAnimation);

        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//实例化一个ColorDrawable颜色为透明

        //实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置弹出窗体的背景
        //this.setBackgroundDrawable(dw);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        int left = anchor.getLeft();
        //int top = anchor.getTop();
        int right = anchor.getRight();

        //获取手机的分辨率
        Display display = activity.getWindowManager().getDefaultDisplay();
        int dWidth = display.getWidth();
        //int dHeight = display.getHeight();

        //获取popupwindow布局的padding值
        int paddingLeft = popView.getPaddingLeft();
        int paddingRight = popView.getPaddingRight();
        popView.measure(w, h);

        RelativeLayout relativeLayout = popView.findViewById(R.id.sanJiao_card);
        relativeLayout.measure(w, h);

        //popupwindow主体的宽度
        int width1 = relativeLayout.getMeasuredWidth();

        //popwindow带padding的宽度
        int widthP = width1 + paddingLeft + paddingRight;

        //popupwindow上方控件的宽度
        int parentWidth = right - left;

        TriangleView triangleView = popView.findViewById(R.id.sanjiaoView);
        //小三角的宽度
        triangleView.measure(w, h);
        int width = triangleView.getMeasuredWidth();

        //小三角最大的marginLeft值
        int maxMarginLeft = width1 - width;

        //控件需调整的margin值
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //判断使用那种计算方式来计算小三角的位移量
        int centerIndex;
        if (parentWidth > widthP) {
            centerIndex = maxMarginLeft / 2;
        } else if ((dWidth - left) > widthP) {
            centerIndex = parentWidth / 2 - width / 2 - paddingLeft;
        } else {
            int rWidth = dWidth - right;
            centerIndex = width1 - (parentWidth / 2 + rWidth - paddingRight);
        }

        if (centerIndex > maxMarginLeft) {
            centerIndex = maxMarginLeft;
        }

        params.setMargins(centerIndex, 0, 0, 0);
        triangleView.setLayoutParams(params);
        super.showAsDropDown(anchor,xoff,yoff);
    }
}

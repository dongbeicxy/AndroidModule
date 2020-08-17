package com.xxx.xxx.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 虎牙 直播小窗口
 * @创建时间 :2020/6/2 14:05
 */
public class HuYaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);
        /*设置窗口样式activity宽高start*/
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.6);   //高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.7);    //宽度设置为屏幕的0.7
        p.alpha = 1.0f;      //设置本身透明度
        p.dimAmount = 0.5f;      //设置窗口外黑暗度
        getWindow().setAttributes(p);
        /*设置窗口样式activity宽高end*/
        setContentView(R.layout.activity_huya);

        TitleBar mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {
                //ToastUtils.show("中间View被点击");
            }

            @Override
            public void onRightClick(View v) {
                //ToastUtils.show("右项View被点击");
            }
        });
    }
}

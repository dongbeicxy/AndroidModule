package com.xxx.xxx.view.activity_share;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 分享 登录 Activity
 * @创建时间 :2020/3/31 17:14
 * <p>
 * XXApplication
 * App Key 2e9ec671bbbc5
 * App Secret  e9ffc2cabad7ea85c20bdd2fa7c4c2c5
 */
public class ShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

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

    //点击分享到微信好友
    public void friend(View view) {

    }

    //点击分享到微信朋友圈
    public void wechat(View view) {

    }

    //点击 微信登录
    public void wxLogin(View view) {

    }
}

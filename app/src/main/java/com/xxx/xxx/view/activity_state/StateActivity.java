package com.xxx.xxx.view.activity_state;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;
import com.xxx.xxx.R;
import com.xxx.xxx.utils.callback.loadsir.EmptyCallback;
import com.xxx.xxx.utils.callback.loadsir.ErrorCallback;
import com.xxx.xxx.utils.callback.loadsir.LoadingCallback;

/**
 * @作者 xzb
 * @描述: 状态 布局 页面
 * @创建时间 :2020/4/2 15:54
 */
public class StateActivity extends Activity {

    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zyad);
        loadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            //点击 除了正常布局 的任何布局  都会 走这个方法
            @Override
            public void onReload(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadService.showCallback(LoadingCallback.class);
                        SystemClock.sleep(500);
                        //显示 正常显示数据 的布局
                        //loadService.showSuccess();
                        loadService.showCallback(EmptyCallback.class);
                    }
                }).start();
            }
            //自定义 各状态 的 布局样式
        }).setCallBack(EmptyCallback.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                //TextView mTvEmpty = view.findViewById(R.id.tv_empty);
                //mTvEmpty.setText("fine, no data. You must fill it!");
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //设置 加载中状态 后的各种状态
                loadService.showCallback(ErrorCallback.class);
            }
        }, 1000);
    }
}

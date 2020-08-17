package com.xxx.xxx.utils.callback.loadsir;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 状态布局 加载中页
 * @创建时间 :2020/4/2 15:14
 */
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}

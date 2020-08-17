package com.xxx.xxx.utils.callback.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 状态布局 空页
 * @创建时间 :2020/4/2 15:14
 */
public class EmptyCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }
}

package com.xxx.xxx.view.activity_shopping_mall.activity_pdd;

import android.util.Log;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.xxx.xxx.R;
import com.xxx.xxx.view.base.LazyFragment;

import butterknife.BindView;

/**
 * @作者 xzb
 * @描述: 我的
 * @创建时间 :2020/3/27 10:14
 */
public class MyFragment extends LazyFragment {
    @BindView(R.id.tv_my)
    TextView textView;

    @Override
    protected int getContentViewId() {
        Log.e("Lazy", "我的加载布局");
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {
        Log.e("Lazy", textView.getText().toString() + "获取数据");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary) .navigationBarColor(R.color.white) .fitsSystemWindows(true).init();
    }
}

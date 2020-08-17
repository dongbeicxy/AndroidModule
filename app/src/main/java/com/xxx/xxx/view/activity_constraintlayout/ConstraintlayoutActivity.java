package com.xxx.xxx.view.activity_constraintlayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.gyf.immersionbar.BarProperties;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.OnBarListener;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxx.xxx.R;
import com.xxx.xxx.presenter.base.BasePresenter;
import com.xxx.xxx.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 xzb
 * @描述:
 * @创建时间 :2020/4/15 16:23
 * <p>
 * https://github.com/OCNYang/Android-Animation-Set   动画库
 */
public class ConstraintlayoutActivity extends BaseActivity implements DrawerLayout.DrawerListener {
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_constraintlayout_total;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).titleBar(R.id.toolbar).setOnBarListener(new OnBarListener() {
            @Override
            public void onBarChange(BarProperties barProperties) {

            }
        }).init();

        //设置 不可滑动出来
        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawer.addDrawerListener(this);
        drawer.openDrawer(GravityCompat.START);

        WindowManager wm = this.getWindowManager();//获取屏幕宽高
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams para = drawer.findViewById(R.id.left).getLayoutParams();//获取drawerlayout的布局
        para.width = width / 3 * 1;//修改宽度
        para.height = height;//修改高度
        drawer.findViewById(R.id.left).setLayoutParams(para); //设置修改后的布局。
    }

    @Override
    protected Toolbar initToolBar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawer.removeDrawerListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}

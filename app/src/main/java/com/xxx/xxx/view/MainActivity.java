package com.xxx.xxx.view;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.graphics.Rect;

import android.os.Bundle;

import android.view.View;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.xxlibrary.XxLibTest;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.CenterListPopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xxx.xxx.R;
import com.xxx.xxx.adapter.MainActivityMenusRecyclerViewAdapter;
import com.xxx.xxx.view.activity_constraintlayout.ConstraintlayoutActivity;
import com.xxx.xxx.view.activity_decompilation.DecompilationActivity;
import com.xxx.xxx.view.activity_loop.LoopActivity;
import com.xxx.xxx.view.activity_pay.PayActivity;
import com.xxx.xxx.view.activity_photograph_album.PhotographAlbumActivity;
import com.xxx.xxx.view.activity_popwindow.PopWindowActivity;
import com.xxx.xxx.view.activity_share.ShareActivity;
import com.xxx.xxx.view.activity_shopping_mall.activity_jd.JDActivity;
import com.xxx.xxx.view.activity_shopping_mall.activity_pdd.PddMainActivity;
import com.xxx.xxx.view.activity_state.StateActivity;
import com.xxx.xxx.view.activity_storage.StorageActivity;
import com.xxx.xxx.view.activity_toutiao.TouTiaoActivity;
import com.xxx.xxx.view.activity_update.UpdatedVersionActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    private CenterListPopupView centerListPopupView = null;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor(R.color.white).navigationBarColor(R.color.white).fitsSystemWindows(true).init();
        //QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_main);
        new XxLibTest().print();
        ButterKnife.bind(this);

        List<String> list = new ArrayList<String>();
        list.add("轮播");
        list.add("网络");
        list.add("刷新加载");
        list.add("图片");
        list.add("文字");
        list.add("系统适配");
        list.add("状态布局");
        list.add("存储");
        list.add("WebView");
        list.add("沉浸状态栏");
        list.add("相册拍照文件");
        list.add("图片裁剪");
        list.add("支付");
        list.add("分享。登录");
        list.add("商城");
        list.add("应用升级");
        list.add("PopWindow");
        list.add("Constraintlayout");
        list.add("反编译apk");
        list.add("安全键盘");
        list.add("仿虎牙直播");
        list.add("今日头条");

        //实例化Adapter
        MainActivityMenusRecyclerViewAdapter mainActivityRecyclerViewAdapter = new MainActivityMenusRecyclerViewAdapter(R.layout.item_main_activity_recyclerview, list);
        //定义布局管理器为Grid管理器，设置一行放3个
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        //设置布局管理器为网格布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        recyclerView.setAdapter(mainActivityRecyclerViewAdapter);
        //设置分隔线
        recyclerView.addItemDecoration(new MyDecoration());

        //设置Item点击事件
        mainActivityRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if ("系统适配".equals(list.get(position))) {
                    startActivity(new Intent(MainActivity.this, SystemAdaptationActivity.class));
                }

                if ("存储".equals(list.get(position))) {
                    startActivity(new Intent(MainActivity.this, StorageActivity.class));
                }

                if ("轮播".equals(list.get(position))) {
                    startActivity(new Intent(MainActivity.this, LoopActivity.class));
                }

                if ("图片".equals(list.get(position))) {
                    startActivity(new Intent(MainActivity.this, ImageViewActivity.class));
                }

                if ("网络".equals(list.get(position))) {
                    startActivity(new Intent(MainActivity.this, NetActivity.class));
                }

                if ("分享。登录".equals(list.get(position))) {
                    startActivity(new Intent(MainActivity.this, ShareActivity.class));
                }

                if ("应用升级".equals(list.get(position))) {
                    startActivity(new Intent(MainActivity.this, UpdatedVersionActivity.class));
                }

                if ("商城".equals(list.get(position))) {
                    centerListPopupView = new XPopup.Builder(MainActivity.this)
                            //.maxWidth(600)
                            .asCenterList("", new String[]{"拼多多", "淘宝", "京东"},
                                    new OnSelectListener() {
                                        @Override
                                        public void onSelect(int position, String text) {
                                            centerListPopupView.dismiss();
                                            Intent intent = new Intent();
                                            if (0 == position) {
                                                intent.setClass(MainActivity.this, PddMainActivity.class);
                                                startActivity(intent);
                                            }
                                            if (2 == position) {
                                                intent.setClass(MainActivity.this, JDActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                    centerListPopupView.show();
                }

                if ("相册拍照文件".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, PhotographAlbumActivity.class);
                    startActivity(intent);
                }

                if ("支付".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, PayActivity.class);
                    startActivity(intent);
                }

                if ("今日头条".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, TouTiaoActivity.class);
                    startActivity(intent);
                }

                if ("状态布局".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, StateActivity.class);
                    startActivity(intent);
                }

                if ("PopWindow".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, PopWindowActivity.class);
                    startActivity(intent);
                }

                if ("WebView".equals(list.get(position))) {
                    new XPopup.Builder(MainActivity.this).asConfirm("微软word在线预览服务", "https://view.officeapps.live.com/op/embed.aspx?src=    +在线word的URL",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    //toast("click confirm");
                                }
                            })
                            .show();
                }

                if ("Constraintlayout".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ConstraintlayoutActivity.class);
                    startActivity(intent);
                }

                if ("反编译apk".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DecompilationActivity.class);
                    startActivity(intent);
                }

                if ("仿虎牙直播".equals(list.get(position))) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, HuYaActivity.class);
                    startActivity(intent);
                }

                if ("沉浸状态栏".equals(list.get(position))) {
//                    ImmersionBar.with(MainActivity.this)
//                            .fullScreen(true)//有导航栏的情况下，activity全屏显示 (上方ImageView与状态栏融为一体)，
//                            .transparentBar()//透明状态栏和导航栏，(全屏图片)
//                            .init();

                    /*
                     * 仿QQ状态栏和标题栏渐变色
                     */
//                    ImmersionBar.with(MainActivity.this).titleBar(R.id.toolbar)
//                            .navigationBarColor(R.color.white)
//                            .init();

//                     <android.support.v7.widget.Toolbar
//                    android:id="@+id/toolbar"
//                    android:layout_width="match_parent"
//                    android:layout_height="?actionBarSize"
//                    android:background="@drawable/shape"
//                    app:title="仿QQ状态栏和标题栏渐变色"
//                    app:titleTextColor="@android:color/white" />

                    //shape.xml

//                    <shape xmlns:android="http://schemas.android.com/apk/res/android"
//                    android:shape="rectangle">
//
//                    <gradient
//                    android:angle="0"
//                    android:centerX="0.7"
//                    android:endColor="@color/shape2"
//                    android:startColor="@color/shape1"
//                    android:centerColor="@color/shape3"
//                    android:type="linear" />

//                  </shape>

                    //仿饿了么主页Fragment效果参考 TabLayout2Activity 代码
                }

            }
        });

        //是否在刷新的时候禁止内容的一切手势操作（默认false）
        smartRefreshLayout.setDisableContentWhenRefresh(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                //完成加载，并设置是否成功
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            //outRect.set()中的参数分别对应左、上、右、下的间隔
            outRect.set(4, 4, 4, 4);
        }
    }
}

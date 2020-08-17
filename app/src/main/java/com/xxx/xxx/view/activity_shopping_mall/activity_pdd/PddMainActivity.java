package com.xxx.xxx.view.activity_shopping_mall.activity_pdd;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.xxx.xxx.R;
import com.xxx.xxx.adapter.PddMainViewPagerAdapter;
import com.xxx.xxx.widget.NoTouchViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;

/**
 * @作者 xzb
 * @描述: 拼多多 APP 主页
 * @创建时间 :2020/3/26 13:45
 */
public class PddMainActivity extends FragmentActivity {

    @BindView(R.id.viewPager)
    NoTouchViewPager noTouchViewPager;

    @BindView(R.id.tab)
    PageNavigationView pageNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_pdd_main);
        ButterKnife.bind(this);

        NavigationController navigationController = pageNavigationView.custom()
                .addItem(newItem(R.mipmap.home, R.mipmap.home, "首页"))
                .addItem(newItem(R.mipmap.follow, R.mipmap.follow, "关注"))
                .addItem(newItem(R.mipmap.classification, R.mipmap.classification, "分类"))
                .addItem(newItem(R.mipmap.chat, R.mipmap.chat, "聊天"))
                .addItem(newItem(R.mipmap.home, R.mipmap.home, "我的"))
                .build();

        //noTouchViewPager.setOffscreenPageLimit(4);

        noTouchViewPager.setAdapter(new PddMainViewPagerAdapter(getSupportFragmentManager(), navigationController.getItemCount()));
        noTouchViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0:
//                        ImmersionBar.with(PddMainActivity.this).keyboardEnable(false).fitsSystemWindows(true).statusBarDarkFont(false).navigationBarColor(R.color.divider).init();
//                        break;
//                    case 1:
//                        ImmersionBar.with(PddMainActivity.this).keyboardEnable(false).fitsSystemWindows(true).statusBarDarkFont(true, 0.2f).navigationBarColor(R.color.white).init();
//                        break;
//                    case 2:
//                        ImmersionBar.with(PddMainActivity.this).keyboardEnable(false).fitsSystemWindows(true).statusBarDarkFont(false).navigationBarColor(R.color.red).init();
//                        break;
//                    case 3:
//                        ImmersionBar.with(PddMainActivity.this).keyboardEnable(true).fitsSystemWindows(true).statusBarDarkFont(true).navigationBarColor(R.color.divider).init();
//                        break;
//                    case 4:
//                        ImmersionBar.with(PddMainActivity.this).keyboardEnable(false).fitsSystemWindows(true).statusBarDarkFont(false).navigationBarColor(R.color.colorPrimary).init();
//                        break;
                // }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(noTouchViewPager);

        //设置消息数
        navigationController.setMessageNumber(1, 8);

        navigationController.setMessageNumber(2, 89);

        navigationController.setMessageNumber(3, 0);

        navigationController.setMessageNumber(4, 125);

        //设置显示小圆点
        navigationController.setHasMessage(0, true);
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(0xFF009688);
        return normalItemView;
    }
}

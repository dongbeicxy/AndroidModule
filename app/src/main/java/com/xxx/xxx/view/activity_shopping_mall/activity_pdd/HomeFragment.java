package com.xxx.xxx.view.activity_shopping_mall.activity_pdd;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.xxx.xxx.R;
import com.xxx.xxx.adapter.PddHomeVPAdapter;
import com.xxx.xxx.view.base.LazyFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @作者 xzb
 * @描述: 首页
 * @创建时间 :2020/3/27 10:14
 */
public class HomeFragment extends LazyFragment {

    private static final String[] CHANNELS = new String[]{"热门", "女装", "鞋包", "手机", "男装", "食品", "母婴", "百货", "内衣", "电器", "家纺","水果","家具","美妆","家装","运动","车品","医药","电脑","海淘"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int getContentViewId() {
        Log.e("Lazy", "首页加载布局");
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        //Log.e("Lazy", textView.getText().toString() + "获取数据");
    }

    @Override
    protected void initEvent() {
        //页卡对应内容
        viewPager.setAdapter(new PddHomeVPAdapter(mDataList));
        //指示器 白色背景
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(Color.RED);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(6.0f);
                indicator.setRoundRadius(0f);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.RED);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary) .navigationBarColor(R.color.white) .fitsSystemWindows(true).init();
    }
}

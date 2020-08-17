package com.xxx.xxx.view.activity_loop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.viewpager2.widget.MarginPageTransformer;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xxx.xxx.R;
import com.xxx.xxx.adapter.LoopAdapter;
import com.xxx.xxx.widget.NumIndicator;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @作者 xzb
 * @描述: 轮播Activity
 * @创建时间 :2020/4/8 18:19
 */
public class LoopActivity extends Activity {

    private Banner banner;

    @BindView(R.id.indicator)
    RoundLinesIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop);
        ButterKnife.bind(this);
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

        banner = (Banner) findViewById(R.id.banner);
        List<String> list = new ArrayList<String>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586351771413&di=59b4d4d7f3e28b3ed4bc8a366634da4b&imgtype=0&src=http%3A%2F%2Farticle.fd.zol-img.com.cn%2Ft_s500x2000%2Fg4%2FM02%2F09%2F05%2FCg-4WVP1oBOICdh6AAJKIoo4-mwAAQskwA0U5MAAko6076.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=754966824,1500366149&fm=11&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586351799633&di=b5288081ac4eb5239928d50b111693f9&imgtype=0&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D775203925%2C1347531130%26fm%3D214%26gp%3D0.jpg");
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1782266252,1396571164&fm=11&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586351771407&di=2b5a77ea86caf217b0cd06c749a332c2&imgtype=0&src=http%3A%2F%2Fwww.52player.com%2Fuploads%2Fallimg%2Fc170223%2F14WTG3E04F-151S8.jpg");
        //设置适配器
        banner.setAdapter(new LoopAdapter(list, this));
        //设置点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                Toast.makeText(LoopActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        //圆角
        //banner.setBannerRound(BannerUtils.dp2px(16));

        //添加画廊效果，可以参考我给的参数自己调试(不要和其他PageTransformer同时使用)
        //banner.setBannerGalleryEffect(25, 40, 0.14f);
        banner.addPageTransformer(new MarginPageTransformer(32));


        //设置组合PageTransformer
        //banner.addPageTransformer(new ZoomOutPageTransformer());
        //banner.addPageTransformer(new DepthPageTransformer());

        //banner.addPageTransformer(new MultipleScaleTransformer(8,0.8f));

        SmartRefreshLayout smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }


    @OnClick({R.id.center, R.id.right, R.id.rec_center, R.id.rec_right, R.id.outside, R.id.number})
    public void click(View view) {
        indicator.setVisibility(View.GONE);
        //移除 指示器
        //banner.removeIndicator();
        switch (view.getId()) {
            case R.id.center:
                banner.setIndicator(new CircleIndicator(this));
                banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
                break;

            case R.id.right:
                banner.setIndicator(new CircleIndicator(this));
                banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
                break;

            case R.id.rec_center:
                banner.setIndicator(new RectangleIndicator(this));
                banner.setIndicatorNormalWidth((int) BannerUtils.dp2px(8));
                banner.setIndicatorSpace((int) BannerUtils.dp2px(4));
                banner.setIndicatorRadius(4);
                banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER);
                break;

            case R.id.rec_right:
                banner.setIndicator(new RectangleIndicator(this));
                banner.setIndicatorNormalWidth((int) BannerUtils.dp2px(8));
                banner.setIndicatorSpace((int) BannerUtils.dp2px(4));
                banner.setIndicatorRadius(4);
                banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
                break;

            case R.id.outside:
                indicator.setVisibility(View.VISIBLE);
                //在布局文件中使用指示器，这样更灵活
                banner.setIndicator(indicator, false);
                banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(12));
                break;

            case R.id.number:
                banner.setIndicator(new NumIndicator(this));
                banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stop();
    }
}

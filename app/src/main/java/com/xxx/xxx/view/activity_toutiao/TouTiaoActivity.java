package com.xxx.xxx.view.activity_toutiao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;
import com.xxx.xxx.R;
import com.xxx.xxx.adapter.TouTiaoActivityTabFragmentAdapter;
import com.xxx.xxx.bean.NewsTabBean;
import com.xxx.xxx.utils.callback.loadsir.EmptyCallback;
import com.xxx.xxx.utils.callback.loadsir.LoadingCallback;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * @作者 xzb
 * @描述: 头条    多Tab 公用 Fragment
 * @创建时间 :2020/6/3 17:11
 */
public class TouTiaoActivity extends FragmentActivity {

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.content_layout)
    LinearLayout content_layout;

    private LoadService loadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toutiao);
        ButterKnife.bind(this);

        loadService = LoadSir.getDefault().register(content_layout, new Callback.OnReloadListener() {
            //点击 除了正常布局 的任何布局  都会 走这个方法
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                //显示 正常显示数据 的布局
                //loadService.showSuccess();
                loadService.showCallback(EmptyCallback.class);
            }
            //自定义 各状态 的 布局样式
        }).setCallBack(EmptyCallback.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                //TextView mTvEmpty = view.findViewById(R.id.tv_empty);
                //mTvEmpty.setText("fine, no data. You must fill it!");
            }
        });

        //getTabs();
    }

    private void getTabs() {
        String url = "http://openapi.look.360.cn/v2/tabs?u=ee264edefb378d36e39aefb407cba66d&sign=ex_85b9ef4e&access_token=143fafe26b5e79d760990e401b40acc1593769597&version=6&device=0";
        Disposable disposable = EasyHttp.get(url)
                .params(new HttpParams())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        Log.e("EasyHttp", e.toString());
                    }

                    @Override
                    public void onSuccess(String string) {
                        if (string != null) {
                            Gson gson = new Gson();
                            NewsTabBean bean = gson.fromJson(string, NewsTabBean.class);

                            Message message = new Message();
                            message.obj = bean.getData();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }
                });


//        OkHttpClient client = new OkHttpClient();
//        String url = "http://openapi.look.360.cn/v2/tabs?u=ee264edefb378d36e39aefb407cba66d&sign=ex_85b9ef4e&access_token=143fafe26b5e79d760990e401b40acc1593769597&version=6&device=0";
//        //默认是get
//        Request request = new Request.Builder().url(url).build();
//        client.newCall(request).enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("OkHttpClient", e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                Gson gson = new Gson();
//                NewsTabBean bean = gson.fromJson(result, NewsTabBean.class);
//
//                Message message = new Message();
//                message.obj = bean.getData();
//                message.what = 1;
//                handler.sendMessage(message);
//            }
//        });
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                initViews((List<NewsTabBean.DataBean>) msg.obj);
                loadService.showSuccess();
            }
        }
    };

    private void initViews(List<NewsTabBean.DataBean> data) {
        List<NewsFragment> fragmentLists = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            NewsFragment fragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("flag", data.get(i).getC());
            fragment.setArguments(bundle);
            fragmentLists.add(fragment);
        }
        //页卡对应内容 (Fragment)
        viewPager.setAdapter(new TouTiaoActivityTabFragmentAdapter(getFragmentManager(), fragmentLists));

        /*******  初始化 Tab 指示器 ******************************/
        //指示器 白色背景
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            //配置 Tab 标题
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(Color.RED);
                simplePagerTitleView.setText(data.get(index).getName());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            //配置 Tab 滑动条
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
}

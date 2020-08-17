package com.xxx.xxx.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import androidx.annotation.NonNull;

import com.xxx.xxx.view.activity_toutiao.NewsFragment;

import java.util.List;


/**
 * Created by hackware on 2016/9/10.
 * 今日头条 页卡 Fragment   页面适配器
 */
public class TouTiaoActivityTabFragmentAdapter extends FragmentStatePagerAdapterCompat {
    private List<NewsFragment> newsFragments;

    public TouTiaoActivityTabFragmentAdapter(@NonNull FragmentManager fm, List<NewsFragment> newsFragments) {
        super(fm);
        this.newsFragments = newsFragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return newsFragments.get(position);
    }

    @Override
    public int getCount() {
        return newsFragments.size();
    }
}

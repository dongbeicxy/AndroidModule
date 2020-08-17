package com.xxx.xxx.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xxx.xxx.view.activity_shopping_mall.activity_pdd.AFragment;
import com.xxx.xxx.view.activity_shopping_mall.activity_pdd.CategoryFragment;
import com.xxx.xxx.view.activity_shopping_mall.activity_pdd.ChatFragment;
import com.xxx.xxx.view.activity_shopping_mall.activity_pdd.FollowFragment;
import com.xxx.xxx.view.activity_shopping_mall.activity_pdd.HomeFragment;
import com.xxx.xxx.view.activity_shopping_mall.activity_pdd.MyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 xzb
 * @描述: 适合于 Fragment数量不多的情况。当某个页面不可见时，该页面对应的View可能会被销毁，但是所有的Fragment都会一直存在于内存中。
 * <p>
 * <p>
 * FragmentStatePagerAdapter(碎片状态适配器)
 * 适合于Fragment数量较多的情况。当页面不可见时， 对应的Fragment实例可能会被销毁，但是Fragment的状态会被保存。
 * @创建时间 :2020/3/26 14:44
 */
public class PddMainViewPagerAdapter extends FragmentPagerAdapter {
    private int size;

    private List<Fragment> fragments;

    public PddMainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.size = behavior;
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FollowFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new ChatFragment());
        fragments.add(new MyFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //return AFragment.newInstance(position + "");
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return size;
    }

    //https://blog.csdn.net/mr_liabill/article/details/48749807
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
    }
}

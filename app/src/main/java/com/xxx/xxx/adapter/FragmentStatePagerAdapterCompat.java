package com.xxx.xxx.adapter;

/**
 * @作者 xzb
 * @描述: 多Tab下    缓存FragmentStatePagerAdapter
 * @创建时间 :2020/6/8 11:22
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

/**
 * Description: Copied from android.support.v4.app.FragmentStatePagerAdapter, Fix bug for that fragments cache not refreshed when adapter items's position changed.
 * FixBug#修复Fragment的位置更新而缓存未更新导致显示错误以及crash的问题
 * Author: xuqingqi
 * E-mail: xuqingqi01@gmail.com
 * Date: 2017/12/8
 */
public abstract class FragmentStatePagerAdapterCompat extends PagerAdapter {

    private static final String TAG = FragmentStatePagerAdapterCompat.class.getSimpleName();
    private static final boolean DEBUG = true;

    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;

    private SparseArray<Fragment.SavedState> mSavedState = new SparseArray<Fragment.SavedState>();
    private SparseArray<Fragment> mFragments = new SparseArray<Fragment>();
    private Fragment mCurrentPrimaryItem = null;

    public FragmentStatePagerAdapterCompat(FragmentManager fm) {
        mFragmentManager = fm;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    public abstract Fragment getItem(int position);

    /**
     * Return a unique identifier for the item at the given position.
     *
     * <p>The default implementation returns the given position.
     * Subclasses should override this method if the positions of items can change.</p>
     *
     * @param position Position within this adapter
     * @return Unique identifier for the item at position
     */
    //添加一个方法，将Fragment和Id绑定在一起而不是position
    public int getItemId(int position) {
        return position;
    }

    //Viewpager显示的页面数据有所改变的回调
    @Override
    public void startUpdate(ViewGroup container) {
        if (container.getId() == View.NO_ID) {
            throw new IllegalStateException("ViewPager with adapter " + this
                    + " requires a view id");
        }
    }

    //初始化一个item数据的时候的回调
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // If we already have this item instantiated, there is nothing
        // to do.  This can happen when we are restoring the entire pager
        // from its saved state, where the fragment manager has already
        // taken care of restoring the fragments we previously had instantiated.
        int itemId = getItemId(position);
        Fragment f = mFragments.get(itemId);
        if (f != null) {
            return f;
        }

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        Fragment fragment = getItem(position);
        if (DEBUG) Log.v(TAG, "Adding item #" + position + ": f=" + fragment);
        Fragment.SavedState fss = mSavedState.get(itemId);
        if (fss != null) {
            //恢复Fragment状态
            fragment.setInitialSavedState(fss);
        }

        fragment.setMenuVisibility(false);
        fragment.setUserVisibleHint(false);
        mFragments.put(itemId, fragment);
        mCurTransaction.add(container.getId(), fragment);

        return fragment;
    }

    //销毁一个item数据的时候会回调
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (DEBUG) Log.v(TAG, "Removing item #" + position + ": f=" + object
                + " v=" + ((Fragment) object).getView());

        int index = mFragments.indexOfValue(fragment);
        if (index >= 0) {
            int itemId = mFragments.keyAt(index);
            mSavedState.put(itemId, fragment.isAdded()
                    //保存Fragment状态
                    ? mFragmentManager.saveFragmentInstanceState(fragment) : null);
            mFragments.remove(itemId);
        }

        mCurTransaction.remove(fragment);
    }

    //设置好当前显示item后的回调
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    //页面数据改变的处理结束后的回调方法
    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mCurTransaction.commitNowAllowingStateLoss();
            } else {
                mCurTransaction.commitAllowingStateLoss();
            }
            mCurTransaction = null;
        }
    }

    //View 是否和 Object有关联关系
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public Parcelable saveState() {
        Bundle state = null;
        if (mSavedState.size() > 0) {
            state = new Bundle();
            for (int i = 0; i < mSavedState.size(); i++) {
                int itemId = mFragments.keyAt(i);
                Fragment.SavedState ss = mSavedState.get(itemId);
                if (ss != null) {
                    String key = "s" + itemId;
                    state.putParcelable(key, ss);
                }
            }
        }
        for (int i = 0; i < mFragments.size(); i++) {
            int itemId = mFragments.keyAt(i);
            Fragment f = mFragments.get(itemId);
            if (f != null && f.isAdded()) {
                if (state == null) {
                    state = new Bundle();
                }
                String key = "f" + itemId;
                mFragmentManager.putFragment(state, key, f);
            }
        }
        return state;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        if (state != null) {
            Bundle bundle = (Bundle) state;
            bundle.setClassLoader(loader);
            mSavedState.clear();
            mFragments.clear();
            Iterable<String> keys = bundle.keySet();
            for (String key : keys) {
                if (key.startsWith("f")) {
                    int index = Integer.parseInt(key.substring(1));
                    Fragment f = mFragmentManager.getFragment(bundle, key);
                    if (f != null) {
                        f.setMenuVisibility(false);
                        mFragments.put(index, f);
                    } else {
                        Log.w(TAG, "Bad fragment at key " + key);
                    }
                } else if (key.startsWith("s")) {
                    int index = Integer.parseInt(key.substring(1));
                    Parcelable parcelable = bundle.getParcelable(key);
                    if (parcelable instanceof Fragment.SavedState) {
                        mSavedState.put(index, (Fragment.SavedState) parcelable);
                    }
                }
            }
        }
    }
}
package com.xxx.xxx.view.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.components.SimpleImmersionFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @作者 xzb
 * @描述: 懒加载 Fragment
 * @创建时间 :2020/3/27 9:52
 */
public abstract class LazyFragment extends SimpleImmersionFragment {
    private Unbinder unbinder;
    //private Context mContext;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = getActivity();
//    }

    protected View rootView;
    private boolean isInitView = false;
    private boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentViewId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        isInitView = true;
        isCanLoadData();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来
        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }
    }

    private void isCanLoadData() {
        //所以条件是view初始化完成并且对用户可见
        if (isInitView && isVisible) {
            initData();
            initEvent();
            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        Log.e("Lazy", "fragment销毁视图");
        super.onDestroyView();
    }


    /**
     * 加载页面布局文件
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 加载要显示的数据
     */
    protected abstract void initData();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();
}

package com.xxx.xxx.presenter.base;

import com.zhouyou.http.EasyHttp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T> {
    //软引用
    protected WeakReference<T> weakReference;
    protected List<Disposable> list;

    //绑定View
    public void attachView(T t) {
        weakReference = new WeakReference<>(t);
        list = new ArrayList<Disposable>();
    }

    //获取 View
    protected T getView() {
        return weakReference.get();
    }

    //判断 是否与View 建立了关联
    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    //解绑View
    public void detachView() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
        //取消 网络请求
        if (null != list && list.size() > 0) {
            for (Disposable disposable : list) {
                EasyHttp.cancelSubscription(disposable);
            }
            list.clear();
            list = null;
        }
    }
}

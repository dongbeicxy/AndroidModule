package com.xxx.xxx.utils.net_request;

/**
 * Created by XZB on 2017/10/3.
 * <p>
 * 网络请求结果 监听器
 */
public interface RequestResultListener<T> {
    /**
     * 成功时回调
     *
     * @param t
     */
    void onSuccess(T t);

    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError(String hint);
}

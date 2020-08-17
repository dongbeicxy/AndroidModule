package com.xxx.xxx.utils.net_request;

import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.xxx.xxx.utils.FileUtils;
import com.xxx.xxx.view.NetActivity;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.body.ProgressResponseCallBack;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.utils.HttpLog;

import java.io.File;

import io.reactivex.disposables.Disposable;

public class HttpUtils {
    /**
     * 数据回调 接口
     */
    public RequestResultCallBackListener requestResultCallBackListener;

    public interface RequestResultCallBackListener<T> {
        public void requestResultCallBackSuccess(T t);

        public void requestResultCallBackError(String hintMessage);
    }

    /**********************************************************************/
    // 使用volatile关键字保其可见性
    private volatile static HttpUtils httpUtils = null;

    private HttpUtils() {
        // private类型的构造函数，保证其他类对象不能直接new一个该对象的实例
    }

    public static HttpUtils getInstance() {
        if (null == httpUtils) {
            synchronized (HttpUtils.class) {
                if ((null == httpUtils)) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    /**
     * 取出 JavaBean 对象
     *
     * @param
     * @param <T>
     * @return
     */
    private static <T> T getJavaBean(Class<T> c, String jsonString) {
        try {
            //T t = new Gson().fromJson(jsonString, c);
            //return t;
            GsonBuilder gsonbuilder = new GsonBuilder();
            gsonbuilder.serializeNulls();
            T t = gsonbuilder.create().fromJson(jsonString, c);
            return t;

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * get 请求
     */
    public static <T> Disposable get(String url, HttpParams params, Class<T> t, final RequestResultCallBackListener requestResultCallBackListener) {
        synchronized (requestResultCallBackListener) {
            Disposable disposable = EasyHttp.get(url)
                    .params(params)
                    .execute(new SimpleCallBack<String>() {
                        @Override
                        public void onError(ApiException e) {
                            requestResultCallBackListener.requestResultCallBackError(e.getMessage());
                        }

                        @Override
                        public void onSuccess(String string) {
                            if (string != null) {
                                requestResultCallBackListener.requestResultCallBackSuccess(getJavaBean(t, string));
                            }
                        }
                    });
            return disposable;
        }
    }

    /**
     * post 请求
     */
    public static <T> Disposable post(String url, HttpParams params, Class<T> t, final RequestResultCallBackListener requestResultCallBackListener) {
        synchronized (requestResultCallBackListener) {
            Disposable disposable = EasyHttp.post(url)
                    .params(params)
                    .execute(new SimpleCallBack<String>() {
                        @Override
                        public void onError(ApiException e) {
                            requestResultCallBackListener.requestResultCallBackError(e.getMessage());
                        }

                        @Override
                        public void onSuccess(String string) {
                            if (string != null) {
                                requestResultCallBackListener.requestResultCallBackSuccess(getJavaBean(t, string));
                            }
                        }
                    });
            return disposable;
        }
    }

    /**
     * 上传文件
     */
    public static <T> Disposable postFile(String url, String paramKey, File file, Class<T> t, final RequestResultCallBackListener requestResultCallBackListener) {
        synchronized (requestResultCallBackListener) {
            Disposable disposable = EasyHttp.post(url)
                    .params(paramKey, file, file.getName(), new ProgressResponseCallBack() {
                        @Override
                        public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {
                            int progress = (int) (bytesWritten * 100 / contentLength);
                            HttpLog.e(progress + "% ");
                            if (done) {
                                //上传完成
                                //Toast.makeText(NetActivity.this, "上传完成", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .execute(new ProgressDialogCallBack<String>(null, false, true) {
                        @Override
                        public void onSuccess(String s) {

                        }

                        @Override
                        public void onError(ApiException e) {
                            super.onError(e);
                            requestResultCallBackListener.requestResultCallBackError(e.getMessage());
                        }
                    });
            return disposable;
        }
    }

    /**
     * 下载文件
     * 文件下载非常简单，没有提供复杂的下载方式例如：下载管理器、断点续传、多线程下载等
     */
    public static <T> Disposable downloadFile(String url, String savePath, Class<T> t, final RequestResultCallBackListener requestResultCallBackListener) {
        synchronized (requestResultCallBackListener) {
            Disposable disposable = EasyHttp.downLoad(url)
                    .savePath(savePath)
                    .saveName(FileUtils.getFileName(url))//不设置默认名字是时间戳生成的
                    .execute(new DownloadProgressCallBack<String>() {
                        @Override
                        public void update(long bytesRead, long contentLength, boolean done) {
                            int progress = (int) (bytesRead * 100 / contentLength);
                            HttpLog.e(progress + "% ");
                            if (done) {//下载完成
                                //Toast.makeText(NetActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onStart() {
                            //开始下载
                            //Toast.makeText(NetActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete(String path) {
                            //下载完成，path：下载文件保存的完整路径
                            //Toast.makeText(NetActivity.this, "下载完成:" + path, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(ApiException e) {
                            //下载失败
                            //Toast.makeText(NetActivity.this, "下载失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            requestResultCallBackListener.requestResultCallBackError(e.getMessage());
                        }
                    });
            return disposable;
        }
    }
}

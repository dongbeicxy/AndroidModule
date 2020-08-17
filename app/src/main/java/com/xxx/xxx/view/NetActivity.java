package com.xxx.xxx.view;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxx.xxx.bean.GetRequestBean;
import com.xxx.xxx.presenter.activity_net.NetActivityPresenterImp;
import com.xxx.xxx.utils.FileUtils;
import com.xxx.xxx.R;
import com.xxx.xxx.utils.LogUtil;
import com.xxx.xxx.view.activity_net.NetActivityView;
import com.xxx.xxx.view.base.BaseActivity;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;
import com.zhouyou.http.utils.HttpLog;

public class NetActivity extends BaseActivity<NetActivityView, NetActivityPresenterImp> implements NetActivityView {

    @Override
    protected NetActivityPresenterImp createPresenter() {
        return new NetActivityPresenterImp(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_net;
    }

    @Override
    protected void initView() {
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
    }

    @Override
    protected Toolbar initToolBar() {
        return null;
    }

    //https://github.com/zhou-you/RxEasyHttp

    /**
     * get请求
     */
    public void get(View view) {
        //presenter.showGetRequestDatas("635840524184357321");

        HttpParams httpParams = new HttpParams();
        httpParams.put("map_type", "google");
        httpParams.put("city_id", "147");
        httpParams.put("is_struct", "1");
        httpParams.put("with_broker_recommend", "0");
        httpParams.put("page_size", "2");
        httpParams.put("entry", "11");
        httpParams.put("source_id", "2");
        httpParams.put("is_ax_partition", "0");
        httpParams.put("page", "1");

        EasyHttp.get("https://appsale.58.com/mobile/v5/sale/property/list")
                .params(httpParams)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        Toast.makeText(NetActivity.this, e.getMessage() + e.getCode(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String string) {
                        if (string != null)
                            Toast.makeText(NetActivity.this, string, Toast.LENGTH_SHORT).show();
                        LogUtil.e("哈哈", string);
                    }
                });


    }

    /**
     * post请求
     */
    public void post(View view) {
//        HttpParams httpParams = new HttpParams();
//        httpParams.put("user_phone", "18842565635");
//        EasyHttp.post("https://www.niudoctrans.com/niuDocTransServer/niudoctrans_api/register/registrSms")
//                .params(httpParams)
//                .execute(new SimpleCallBack<String>() {
//                    @Override
//                    public void onError(ApiException e) {
//                        Toast.makeText(NetActivity.this, e.getMessage() + e.getCode(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onSuccess(String string) {
//                        if (string != null)
//                            Toast.makeText(NetActivity.this, string, Toast.LENGTH_SHORT).show();
//                    }
//                });

        HttpParams httpParams = new HttpParams();
        httpParams.put("lsplate", "粤A713H8");
        //lstype
        httpParams.put("lstype", "02");
        httpParams.put("appkey", "77db10f4fb1e90a3");
        EasyHttp.post("https://api.jisuapi.com/carinsurance2/query")
                .params(httpParams)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        Toast.makeText(NetActivity.this, e.getMessage() + e.getCode(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String string) {
                        if (string != null)
                            Toast.makeText(NetActivity.this, string, Toast.LENGTH_SHORT).show();
                        LogUtil.e("哈哈", string);
                    }
                });
    }

    /**
     * 上传文件 请求
     */
    public void postFile(View view) {
       /* File file = new File(Environment.getExternalStorageDirectory().getPath() + "/1.jpg");
        EasyHttp.post("index.php/Home/index/uploadImg")
                .params("img", file, file.getName(), new ProgressResponseCallBack() {
                    @Override
                    public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {
                        int progress = (int) (bytesWritten * 100 / contentLength);
                        HttpLog.e(progress + "% ");
                        if (done) {//上传完成
                            Toast.makeText(NetActivity.this, "上传完成", Toast.LENGTH_SHORT).show();
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
                    }
                });*/
    }

    /**
     * 下载文件 请求
     * <p>
     * 文件下载非常简单，没有提供复杂的下载方式例如：下载管理器、断点续传、多线程下载等
     */
    public void downloadFile(View view) {
        String url = "https://www.zhipin.com/wapi/zpgeek/resume/preview/download4geek?type=docx&expectId=e16a87fb74cdb8cb1Xx_2N20Elo~&ka=user_resume_doc_download";
        EasyHttp.downLoad(url)
                .savePath(FileUtils.getFolderOrFilePath(this, "XXApplication", false, ""))
                .saveName(FileUtils.getFileName(url))//不设置默认名字是时间戳生成的
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        HttpLog.e(progress + "% ");
                        if (done) {//下载完成
                            Toast.makeText(NetActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onStart() {
                        //开始下载
                        Toast.makeText(NetActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(String path) {
                        //下载完成，path：下载文件保存的完整路径
                        Toast.makeText(NetActivity.this, "下载完成:" + path, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ApiException e) {
                        //下载失败
                        Toast.makeText(NetActivity.this, "下载失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void showGetRequestDatas(GetRequestBean getRequestBean) {
        Toast.makeText(NetActivity.this, getRequestBean.getData(), Toast.LENGTH_SHORT).show();
    }

//    取消请求
//            通过Disposable取消
//    每个请求前都会返回一个Disposable，取消订阅就可以取消网络请求，如果是带有进度框的网络请求，则不需要手动取消网络请求，会自动取消。
//
//    Disposable mSubscription = EasyHttp.get(url).execute(callback);
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EasyHttp.cancelSubscription(mSubscription);
//    }
}

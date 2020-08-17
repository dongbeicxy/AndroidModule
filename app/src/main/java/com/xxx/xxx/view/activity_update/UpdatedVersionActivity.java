package com.xxx.xxx.view.activity_update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxapplication.android7library.FileProvider7;
import com.xxx.xxx.R;
import com.xxx.xxx.bean.new_version.NewVersion;
import com.xxx.xxx.utils.FileUtils;
import com.xxx.xxx.utils.UpdatedVersionDetector;
import com.xxx.xxx.widget.dialog.AppUpgradeDialog;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.HttpLog;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * @作者 xzb
 * @描述: 版本更新 Activity
 * @创建时间 :2020/4/11 16:50
 */
public class UpdatedVersionActivity extends Activity {

    private Disposable disposable;

    private AppUpgradeDialog appUpgradeDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

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

        //模拟 最新版本apk   json  数据
        NewVersion newVersion = new NewVersion();
        NewVersion.DataBean dataBean = new NewVersion.DataBean();
        dataBean.setVersion(2);
        dataBean.setUrl("http://p.gdown.baidu.com/2921dc37bddc53e4e94ae9dc5df07fa952c347683c2ca4f1bac79abdfb4467e59ca1992558f1447114aec8ad9ca3c669207306fed51922d44eb1ebc80fcb59e3e8cfe771dae6cd9921fade9424a21a0b32390d31699fbf9cad2c244d8a4b48b77a8b03492dbbf66d7b988b1d2b1d45e51b0861f3598455c16fa846b3da3e54e4fe95ccd0cdca89582093102d7e6c0d3eca6923c91f21c2d850d70f2208aed10cc6cd5ddf089604f76e1dc633457da3967fdac2a3a42e8da4558eaf53fedecbd0d88a4008064afd5c69ac25aec9cf4674e70b33a3357d05d8942af0120f883834a448fcadaa785d990ea3266804b51d42");
        newVersion.setData(dataBean);

        UpdatedVersionDetector updatedVersionDetector = new UpdatedVersionDetector(this, newVersion, new UpdatedVersionDetector.UpdatedVersionDetectorNoticeListener() {
            @Override
            public void showVersionUpdateDiaLog(NewVersion newVersion) {
                appUpgradeDialog = new AppUpgradeDialog(UpdatedVersionActivity.this, new AppUpgradeDialog.DownLoadApkListener() {
                    @Override
                    public void downLoadApk() {
                        downLoadApkFile(newVersion.getData().getUrl());
                    }
                });
                appUpgradeDialog.show();
            }

            @Override
            public void showHint(String hint) {

            }
        });
        updatedVersionDetector.startCheck("");
    }

    private void downLoadApkFile(String url) {
        disposable = EasyHttp.downLoad(url)
                .savePath(FileUtils.getFolderOrFilePath(this, "XXApplication", true, "yyb.apk"))
                .saveName("yyb.apk")//不设置默认名字是时间戳生成的
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        HttpLog.e(progress + "% ");
                        if (done) {//下载完成
                            Toast.makeText(UpdatedVersionActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onStart() {
                        //开始下载
                        Toast.makeText(UpdatedVersionActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(String path) {
                        //下载完成，path：下载文件保存的完整路径
                        Toast.makeText(UpdatedVersionActivity.this, "下载完成:" + path, Toast.LENGTH_SHORT).show();
                        //安装
                        installApk(path);
                    }

                    @Override
                    public void onError(ApiException e) {
                        //下载失败
                        Toast.makeText(UpdatedVersionActivity.this, "下载失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void installApk(String path) {
        File file = new File(path);
        //TODO  判断文件完整性
        if (!file.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        FileProvider7.setIntentDataAndType(this, intent, "application/vnd.android.package-archive", file, true);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EasyHttp.cancelSubscription(disposable);
    }
}

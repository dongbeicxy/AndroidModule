package com.xxx.xxx.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xxx.xxx.R;
import com.xxx.xxx.utils.permission.PermissionList;

import io.reactivex.functions.Consumer;

/**
 * @作者 xzb
 * @描述: 系统适配  页面
 * @创建时间 :2020/4/3 10:28
 */
public class SystemAdaptationActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_adaptation);

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

    //权限 申请
    @SuppressLint("CheckResult")
    public void jurisdictionApply(View view) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(PermissionList.permissionsGroup)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Toast.makeText(SystemAdaptationActivity.this, "全部授权", Toast.LENGTH_SHORT).show();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                            Toast.makeText(SystemAdaptationActivity.this, "用户拒绝了" + permission.name + "，没有选中『不再询问』", Toast.LENGTH_SHORT).show();
                        } else {
                            // 用户拒绝了该权限，而且选中『不再询问』那么下次启动时，就不会提示出来了，
                            Toast.makeText(SystemAdaptationActivity.this, "用户拒绝了" + permission.name + "，而且选中『不再询问』", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 点击 8.0安装apk
     *
     * @param view
     */
    public void installApk(View view) {
        installAPK();
    }


    private static final int REQUEST_CODE_UNKNOWN_APP = 100;

    private void installAPK() {

        if (Build.VERSION.SDK_INT >= 26) {
            boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (hasInstallPermission) {
                //安装应用
                Toast.makeText(this, "安装应用", Toast.LENGTH_SHORT).show();
            } else {
                //跳转至“安装未知应用”权限界面，引导用户开启权限
                Uri selfPackageUri = Uri.parse("package:" + this.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, selfPackageUri);
                startActivityForResult(intent, REQUEST_CODE_UNKNOWN_APP);
            }
        } else {
            //安装应用
            Toast.makeText(this, "安装应用", Toast.LENGTH_SHORT).show();
        }

    }

    //接收“安装未知应用”权限的开启结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_UNKNOWN_APP) {
            installAPK();
        }
    }
}

package com.xxx.xxx.utils;

import android.content.Context;
import android.util.Log;

import com.xxx.xxx.bean.new_version.NewVersion;
import com.xxx.xxx.view.MainActivity;

/**
 * @author 作者 :邢志标
 * @version 创建时间：2016年6月8日 下午2:06:23
 * <p>
 * 类说明:版本更新 管理器
 */
public class UpdatedVersionDetector {
    private Context context;
    // 服务器最新版本对象
    private NewVersion newVersion;

    public interface UpdatedVersionDetectorNoticeListener {
        //显示  更新 Dialog
        void showVersionUpdateDiaLog(NewVersion newVersion);

        void showHint(String hint);
    }

    private UpdatedVersionDetectorNoticeListener updatedVersionDetectorNoticeListener;

    public UpdatedVersionDetector(Context context, NewVersion newVersion, UpdatedVersionDetectorNoticeListener updatedVersionDetectorNoticeListener) {
        this.updatedVersionDetectorNoticeListener = updatedVersionDetectorNoticeListener;
        this.context = context;
        this.newVersion = newVersion;
        Log.e("下载地址", newVersion.getData().getUrl());
    }

    /**
     * 检查版本更新
     */
    public void startCheck(String activityFlag) {
        // 如果 服务器为8  本地为7  会执行
        if (isHaveUpdate()) {
            // 显示软件更新对话框
            showUpdateDialog(newVersion);
        } else {
            if (activityFlag.equalsIgnoreCase(MainActivity.class.getSimpleName())) {
                return;
            } else {
                if (null != updatedVersionDetectorNoticeListener) {
                    updatedVersionDetectorNoticeListener.showHint("已是最新版本");
                }
            }
        }
    }

    /**
     * @创建者 邢志标
     * @创建时间 2018/9/27  14:48
     * @修改者 邢志标
     * @方法描述 更新 关于页面 UI
     */
    public boolean isHaveNewVersion() {
        boolean flag = false;

        // 如果 服务器为8  本地为7  会执行
        if (isHaveUpdate()) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }


    /**
     * 检查软件是否有更新版本
     */
    private boolean isHaveUpdate() {
        // 获取当前软件版本
        int nowVersionCode = getNowVersionCode(context);

        // 获取服务器最新apk版本号
        int serverCode = Integer.valueOf(newVersion.getData().getVersion());

        // 版本判断
        if (serverCode > nowVersionCode) {
            return true;
        }
        return false;
    }

    /**
     * 获取APP当前版本号
     */
    private int getNowVersionCode(Context context) {
        int versionCode = 0;
        try {
            //返回应用的上下文，生命周期是整个应用，应用摧毁它才摧毁
            versionCode = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 显示软件更新对话框
     */
    private void showUpdateDialog(NewVersion release) {
        if (null != updatedVersionDetectorNoticeListener) {
            updatedVersionDetectorNoticeListener.showVersionUpdateDiaLog(release);
        }
    }
}

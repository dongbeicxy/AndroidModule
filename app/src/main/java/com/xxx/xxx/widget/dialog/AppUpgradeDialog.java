package com.xxx.xxx.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 应用升级 对话框
 * @创建时间 :2020/4/9 11:41
 */
public class AppUpgradeDialog extends Dialog {

    //立即升级 按钮
    private TextView update;

    public interface DownLoadApkListener {
        void downLoadApk();
    }

    private DownLoadApkListener downLoadApkListener;

    public AppUpgradeDialog(@NonNull Context context, DownLoadApkListener downLoadApkListener) {
        super(context, R.style.Dialog);
        this.downLoadApkListener = downLoadApkListener;
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_app_upgrade);
        //伴你 居底 半透明 高度成比例 dialog 示例代码
        //getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        //        backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
        //        getWindow().setLayout(
        //        getWindow().getContext().getResources().getDisplayMetrics().widthPixels,
        //                (int) (getWindow().getContext().getResources().getDisplayMetrics().widthPixels * 1.00));
        //        //居于底部
        //        getWindow().setGravity(Gravity.BOTTOM);

        //初始化界面控件
        initView();
        //初始化界面数据
        refreshView();
        //初始化界面控件的事件
        initEvent();


    }

    private void initView() {
        update = findViewById(R.id.update);
    }

    private void refreshView() {
    }

    private void initEvent() {
        //检查 权限
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (null != downLoadApkListener) {
                    downLoadApkListener.downLoadApk();
                }
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}

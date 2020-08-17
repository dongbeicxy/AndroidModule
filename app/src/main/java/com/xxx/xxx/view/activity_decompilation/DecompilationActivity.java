package com.xxx.xxx.view.activity_decompilation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 反编译
 * @创建时间 :2020/4/23 9:32
 */
public class DecompilationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decompilation);

        TitleBar mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }
}

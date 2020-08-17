package com.xxx.xxx.view.activity_pay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 支付
 * @创建时间 :2020/3/31 18:23
 */
public class PayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

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

//        TextView textView = (TextView) findViewById(R.id.cTextView);
//        String  s = "这里是注水井数据";;
//        new JiangeUtil(textView, s, 800);
    }

    //微信支付
    public void wxPay(View view) {

    }

    //支付宝 支付
    public void zfbPay(View view) {

    }

    class JiangeUtil{
        private TextView tv;
        private String s;
        private int length;
        private long time;
        private  int n = 0;
        private int nn;

        public JiangeUtil(TextView tv, String s, long time) {
            this.tv = tv;//textview
            this.s = s;//字符串
            this.time = time;//间隔时间
            this.length = s.length();
            startTv(n);//开启线程
        }

        public void startTv(final int n) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final String stv = s.substring(0, n);//截取要填充的字符串
                                tv.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText(stv);
                                    }
                                });
                                Thread.sleep(time);//休息片刻
                                nn = n + 1;//n+1；多截取一个
                                if (nn <= length) {//如果还有汉子，那么继续开启线程，相当于递归的感觉
                                    startTv(nn);
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

            ).start();
        }
    }
}

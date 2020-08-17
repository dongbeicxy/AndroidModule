package com.xxx.xxx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: 自定义组件：购买数量，带减少增加按钮
 * @创建时间 :2020/3/30 15:20
 */
public class AmountView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goods_storage = 993; //商品库存

    private OnAmountChangeListener mListener;

    private TextView etAmount;
    private TextView btnDecrease;
    private TextView btnIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (TextView) findViewById(R.id.etAmount);
        btnDecrease = (TextView) findViewById(R.id.btnDecrease);
        btnIncrease = (TextView) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");
            }
        }
        etAmount.clearFocus();
        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}


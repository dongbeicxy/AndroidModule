package com.xxx.xxx.view.activity_popwindow;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import android.view.LayoutInflater;
import android.view.View;

import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import com.qmuiteam.qmui.skin.QMUISkinManager;

import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import com.xxx.xxx.R;
import com.xxx.xxx.widget.BubblePopupWindow;
import com.xxx.xxx.widget.popwindow.BottomPopWindow;
import com.xxx.xxx.widget.popwindow.MenuPopWindow;

/**
 * @作者 xzb
 * @描述:
 * @创建时间 :2020/4/13 17:30
 */
public class PopWindowActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popwindow);

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

    public void left_top(View view) {
        MenuPopWindow popupWindow = new MenuPopWindow(this);
        popupWindow.showAsDropDown(view, 0, 0);
    }

    public void right_top(View view) {
        MenuPopWindow popupWindow = new MenuPopWindow(this);
        popupWindow.showAsDropDown(view, 0, 0);
    }

    public void left_below(View view) {
        View bubbleView = LayoutInflater.from(this).inflate(R.layout.layout_popup_view, null);
        BubblePopupWindow leftBottomWindow = new BubblePopupWindow(this);
        leftBottomWindow.setBubbleView(bubbleView);
        leftBottomWindow.show(view);
    }

    public void center(View view) {
        BottomPopWindow popupWindow = new BottomPopWindow(this);
        popupWindow.showAtLocation(findViewById(R.id.content_layout), Gravity.BOTTOM, 0, 0);
    }

    public void right_below(View view) {
        showSimpleBottomSheetList(
                false, false, false, null,
                3, false, false);

    }


    private void showSimpleBottomSheetList(boolean gravityCenter,
                                           boolean addCancelBtn,
                                           boolean withIcon,
                                           CharSequence title,
                                           int itemCount,
                                           boolean allowDragDismiss,
                                           boolean withMark) {
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        builder.setGravityCenter(gravityCenter)
                .setSkinManager(QMUISkinManager.defaultInstance(PopWindowActivity.this))
                .setTitle(title)
                .setAddCancelBtn(addCancelBtn)
                .setAllowDrag(allowDragDismiss)
                .setNeedRightMark(withMark)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        Toast.makeText(PopWindowActivity.this, "Item " + (position + 1), Toast.LENGTH_SHORT).show();
                    }
                });
        if (withMark) {
            builder.setCheckedIndex(40);
        }
        for (int i = 1; i <= itemCount; i++) {
            if (withIcon) {
                builder.addItem(ContextCompat.getDrawable(PopWindowActivity.this, R.mipmap.ic_launcher), "Item " + i);
            } else {
                builder.addItem("Item " + i);
            }

        }
        builder.build().show();
    }
}

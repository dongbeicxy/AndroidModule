package com.xxx.xxx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xxx.xxx.R;

/**
 * @作者 xzb
 * @描述: Android更多状态的布局Layout，可随意切换状态的Layout，加载中、加载失败、空布局...
 * @创建时间 :2020/6/8 15:27
 */
public class CustomStateLayout extends RelativeLayout {

    private Context context;
    private final int NORMAL = 0;    //常规状态
    private final int LOADING = 1;   //加载中状态
    private final int LOADFAIL = 2;  //加载失败状态
    private final int LOADERROR = 3; //加载错误状态
    private final int LOADEMPTY = 4; //内容为空状态

    public CustomStateLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CustomStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CustomStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private View RelativeLayoutLoadIng, RelativeLayoutLoadFin, RelativeLayoutLoadError, RelativeLayoutLoadEmpty;

    private LayoutInflater inflate;

    private void init() {
        inflate = LayoutInflater.from(context);
    }


    private void setLoadFail() {
        if (RelativeLayoutLoadFin == null) {
            View v = this.inflate.inflate(R.layout.layout_timeout, null);
            RelativeLayoutLoadFin = v.findViewById(R.id.ll_timeout);
            this.addView(RelativeLayoutLoadFin, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            RelativeLayoutLoadFin.setVisibility(VISIBLE);
        }
    }

    private void setLoadIng() {
        if (RelativeLayoutLoadIng == null) {
            View v = this.inflate.inflate(R.layout.layout_loading, null);
            RelativeLayoutLoadIng = v.findViewById(R.id.ll_loading);
            this.addView(RelativeLayoutLoadIng, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            RelativeLayoutLoadIng.setVisibility(VISIBLE);
        }
    }


    private void setLoadError() {
        if (RelativeLayoutLoadError == null) {
            View v = this.inflate.inflate(R.layout.layout_error, null);
            RelativeLayoutLoadError = v.findViewById(R.id.ll_error);
            this.addView(RelativeLayoutLoadError, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            RelativeLayoutLoadError.setVisibility(VISIBLE);
        }
    }

    private void setLoadEmpty() {
        if (RelativeLayoutLoadEmpty == null) {
            View v = this.inflate.inflate(R.layout.layout_empty, null);
            RelativeLayoutLoadEmpty = v.findViewById(R.id.ll_empty);
            this.addView(RelativeLayoutLoadEmpty, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            RelativeLayoutLoadEmpty.setVisibility(VISIBLE);
        }
    }

    private void hideLoadFail() {
        if (RelativeLayoutLoadFin != null) {
            RelativeLayoutLoadFin.setVisibility(GONE);
        }
    }

    private void hideLoadIng() {
        if (RelativeLayoutLoadIng != null) {
            RelativeLayoutLoadIng.setVisibility(GONE);
        }
    }

    private void hideLoadError() {
        if (RelativeLayoutLoadError != null) {
            RelativeLayoutLoadError.setVisibility(GONE);
        }
    }

    private void hideLoadEmpty() {
        if (RelativeLayoutLoadEmpty != null) {
            RelativeLayoutLoadEmpty.setVisibility(GONE);
        }
    }


    public void showLoadIng() {
        setState(LOADING);
    }

    public void showLoadFail() {
        setState(LOADFAIL);
    }

    public void showLoadError() {
        setState(LOADERROR);
    }

    public void showLoadEmpty() {
        setState(LOADEMPTY);
    }

    public void hideAllState() {
        setState(NORMAL);
    }


    private void setState(int state) {
        switch (state) {
            case NORMAL:
                hideLoadIng();
                hideLoadFail();
                hideLoadError();
                hideLoadEmpty();
                break;
            case LOADING:
                setLoadIng();
                hideLoadFail();
                hideLoadError();
                hideLoadEmpty();
                break;
            case LOADFAIL:
                hideLoadIng();
                setLoadFail();
                hideLoadError();
                hideLoadEmpty();
                break;
            case LOADERROR:
                hideLoadIng();
                hideLoadFail();
                setLoadError();
                hideLoadEmpty();
                break;
            case LOADEMPTY:
                hideLoadIng();
                hideLoadFail();
                hideLoadError();
                setLoadEmpty();
                break;
            default:
                hideLoadIng();
                hideLoadFail();
                hideLoadError();
                hideLoadEmpty();
                break;
        }
    }
}

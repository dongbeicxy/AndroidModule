package com.xxx.xxx.view.base;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xxx.xxx.R;
import com.xxx.xxx.presenter.base.BasePresenter;

import butterknife.ButterKnife;

//https://mp.weixin.qq.com/s?__biz=MzIzODE2Mjc4MA==&mid=2652879782&idx=1&sn=f9751dc0ffb407e0d76045ed55382ec3&chksm=f2d69d95c5a11483bb37c301a44932888d0b70bfe1c3545350d85cc44970e0cddfc51a2fec49&scene=0&xtrack=1#rd
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {
    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int initLayout();

    protected abstract void initView();

    protected Toolbar toolbar;

    protected abstract Toolbar initToolBar();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(initLayout());
        ButterKnife.bind(this);
        initView();
        toolbar = initToolBar();
        if (null != toolbar) {
            toolbar.setTitle("");
            //设置导航图标要在setSupportActionBar方法之后
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }
        //创建Presenter
        presenter = createPresenter();
        if (null != presenter) {
            presenter.attachView((V) this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != presenter) {
            presenter.detachView();
        }
    }
}

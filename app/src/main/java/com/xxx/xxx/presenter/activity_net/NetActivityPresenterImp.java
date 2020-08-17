package com.xxx.xxx.presenter.activity_net;

import android.util.Log;

import com.xxx.xxx.bean.GetRequestBean;
import com.xxx.xxx.model.activity_net.NetActivityModelImp;
import com.xxx.xxx.presenter.base.BasePresenter;
import com.xxx.xxx.utils.net_request.RequestResultListener;
import com.xxx.xxx.view.activity_net.NetActivityView;

import io.reactivex.disposables.Disposable;

public class NetActivityPresenterImp extends BasePresenter<NetActivityView> implements NetActivityPresenter {

    private NetActivityView netActivityView;
    private NetActivityModelImp netActivityModelImp;

    public NetActivityPresenterImp(NetActivityView netActivityView) {
        this.netActivityView = netActivityView;
        netActivityModelImp = new NetActivityModelImp();
    }

    @Override
    public void showGetRequestDatas(String rand) {
        Disposable disposable = netActivityModelImp.showGetRequestDatas(rand, new RequestResultListener<GetRequestBean>() {
            @Override
            public void onSuccess(GetRequestBean getRequestBean) {
                netActivityView.showGetRequestDatas(getRequestBean);
            }

            @Override
            public void onError(String hint) {
                Log.e("错误", hint);
            }
        });
        list.add(disposable);
    }
}

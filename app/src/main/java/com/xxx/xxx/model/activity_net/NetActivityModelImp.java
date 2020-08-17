package com.xxx.xxx.model.activity_net;

import com.xxx.xxx.bean.GetRequestBean;
import com.xxx.xxx.utils.net_request.HttpUtils;
import com.xxx.xxx.utils.net_request.RequestResultListener;
import com.zhouyou.http.model.HttpParams;

import io.reactivex.disposables.Disposable;

public class NetActivityModelImp implements NetActivityModel {
    @Override
    public Disposable showGetRequestDatas(String rand, RequestResultListener requestResultListener) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("rand", rand);
        String url = "https://ditu.amap.com/service/pl/pl.json";

        return HttpUtils.getInstance().get(url, httpParams, GetRequestBean.class, new HttpUtils.RequestResultCallBackListener<GetRequestBean>() {
            @Override
            public void requestResultCallBackSuccess(GetRequestBean getRequestBean) {
                requestResultListener.onSuccess(getRequestBean);
            }

            @Override
            public void requestResultCallBackError(String hintMessage) {
                requestResultListener.onError(hintMessage);
            }
        });
    }
}

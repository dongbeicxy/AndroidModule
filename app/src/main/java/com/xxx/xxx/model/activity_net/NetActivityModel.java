package com.xxx.xxx.model.activity_net;

import com.xxx.xxx.utils.net_request.RequestResultListener;

import io.reactivex.disposables.Disposable;

public interface NetActivityModel {
    Disposable showGetRequestDatas(String rand, RequestResultListener requestResultListener);
}

package com.xxx.xxx.view.activity_toutiao;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xxx.xxx.R;
import com.xxx.xxx.adapter.NewsFragmentRecyclerViewAdapter;
import com.xxx.xxx.bean.News360Bean;
import com.xxx.xxx.widget.CustomStateLayout;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @作者 xzb
 * @描述: 新闻 Fragment
 * @创建时间 :2020/6/8 10:21
 */
public class NewsFragment extends Fragment {
    private View rootView;
    private String c;

    private RecyclerView rv_news;
    private NewsFragmentRecyclerViewAdapter newsFragmentRecyclerViewAdapter;
    //总数据源
    private List<News360Bean.DataBean.ResBean> allList = new ArrayList<>();

    private CustomStateLayout customStateLayout;
    private SmartRefreshLayout smartRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.item_news_fragment, container, false);
        return rootView;
    }

    //onCreateView是创建的时候调用，onViewCreated是在onCreateView后被触发的事件，前后关系
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        if (0 == allList.size()) {
            initData(true);
        } else {
            customStateLayout.hideAllState();
        }
    }

    private void initView() {
        customStateLayout = (CustomStateLayout) rootView.findViewById(R.id.state_layout);
        customStateLayout.showLoadIng();
        rv_news = rootView.findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_news.setLayoutManager(layoutManager);
        newsFragmentRecyclerViewAdapter = new NewsFragmentRecyclerViewAdapter(allList);
        rv_news.setAdapter(newsFragmentRecyclerViewAdapter);

        smartRefreshLayout = rootView.findViewById(R.id.smartRefreshLayout);
        //是否在刷新的时候禁止内容的一切手势操作（默认false）
        smartRefreshLayout.setDisableContentWhenRefresh(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData(true);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                initData(false);
            }
        });
    }

    //http://openapi.look.360.cn/v2/list?u=b09aeb5ea20a50af3d9b689f8a92386e&n=10&sign=ex_85b9ef4e&access_token=111d0720084c26d9f17ba6ead470d671594186645&c=outstanding&f=json&version=7&device=0&sv=1&usid=null
    private void initData(boolean isRefresh) {
        String url = "http://openapi.look.360.cn/v2/list?u=b09aeb5ea20a50af3d9b689f8a92386e&n=20&sign=ex_85b9ef4e&access_token=111d0720084c26d9f17ba6ead470d671594186645&c="
                + getArguments().getString("flag") + "&f=json&version=7&device=0&sv=1&usid=null";
        Log.e("url",url);
        Disposable disposable = EasyHttp.get(url)
                .params(new HttpParams())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        Log.e("EasyHttp", e.toString());
                        customStateLayout.showLoadError();
                    }

                    @Override
                    public void onSuccess(String string) {
                        if (string != null) {
                            try {
                                Log.e("数据",string);
                                JSONObject jsonObject = new JSONObject(string);
                                int error = jsonObject.getInt("errno");
                                if (0 == error) {
                                    Gson gson = new Gson();
                                    News360Bean bean = gson.fromJson(string, News360Bean.class);
                                    if (isRefresh) {
                                        allList.clear();
                                        smartRefreshLayout.finishRefresh(true);//传入true表示刷新成功
                                    }
                                    smartRefreshLayout.finishLoadMore(true);//传入true表示加载成功
                                    for (int i = 0; i < bean.getData().getRes().size(); i++) {
                                        bean.getData().getRes().get(i).setItemType(picNum(bean.getData().getRes().get(i).getI()));
                                        allList.add(bean.getData().getRes().get(i));
                                    }
                                    newsFragmentRecyclerViewAdapter.notifyDataSetChanged();
                                    customStateLayout.hideAllState();
                                } else {
                                    if (isRefresh) {
                                        smartRefreshLayout.finishRefresh(false);//传入false表示刷新失败
                                    }
                                    smartRefreshLayout.finishLoadMore(false);//传入false表示加载失败
                                    customStateLayout.showLoadEmpty();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    /**
     * 判断图片个数
     *
     * @param string
     * @return
     */
    private int picNum(String string) {
        if (string == null || string.length() == 0) {
            return -1;
        }
        int i = string.length() - string.replace("|", "").length();
        return i;
    }
}

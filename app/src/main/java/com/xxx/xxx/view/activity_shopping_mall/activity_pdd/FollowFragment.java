package com.xxx.xxx.view.activity_shopping_mall.activity_pdd;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.xxx.xxx.R;
import com.xxx.xxx.adapter.MenusRecyclerViewAdapter;
import com.xxx.xxx.bean.HomePagerMenus;
import com.xxx.xxx.view.base.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @作者 xzb
 * @描述: 关注
 * @创建时间 :2020/3/27 10:14
 */
public class FollowFragment extends LazyFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.parent_layout)
    RelativeLayout parent_layout;

    @BindView(R.id.lineIndicator)
    View lineIndicator;

    @Override
    protected int getContentViewId() {
        Log.e("Lazy", "关注加载布局");
        return R.layout.fragment_follow;
    }

    @Override
    protected void initData() {
        List<String> nameList = new ArrayList<String>();
        nameList.add("限时秒杀");
        nameList.add("充值中心");
        nameList.add("断码清仓");
        nameList.add("医药馆");
        nameList.add("新衣馆");
        nameList.add("现金签到");
        nameList.add("多多果园");
        nameList.add("多多赚大钱");
        nameList.add("9块9特卖");
        nameList.add("电器城");
        nameList.add("多多爱消除");
        nameList.add("爱逛街");
        nameList.add("天天领现金");
        nameList.add("砍价免费拿");
        nameList.add("省钱月卡");
        nameList.add("每日好店");

        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.mipmap.wazi);
        imageList.add(R.mipmap.kuzi);
        imageList.add(R.mipmap.qunzi);
        imageList.add(R.mipmap.lianmaoweiyi);
        imageList.add(R.mipmap.bao);
        imageList.add(R.mipmap.xie);
        imageList.add(R.mipmap.yurongfu);
        imageList.add(R.mipmap.neiyi);
        imageList.add(R.mipmap.maozi);
        imageList.add(R.mipmap.weijin);
        imageList.add(R.mipmap.fengyi);
        imageList.add(R.mipmap.kucha);
        imageList.add(R.mipmap.weiyi);
        imageList.add(R.mipmap.yurongfu);
        imageList.add(R.mipmap.lianmaoweiyi);
        imageList.add(R.mipmap.kuzi);

        List<HomePagerMenus> menus = new ArrayList<HomePagerMenus>();
        for (int i = 0; i < imageList.size(); i++) {
            HomePagerMenus homePagerMenus = new HomePagerMenus(nameList.get(i), imageList.get(i));
            menus.add(homePagerMenus);
        }

        //横向网格列表 2行
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.HORIZONTAL, false);
        //设置布局管理器为网格布局管理器
        recyclerView.setLayoutManager(layoutManager);
        MenusRecyclerViewAdapter menusRecyclerViewAdapter = new MenusRecyclerViewAdapter(this, R.layout.item_follow_recyclerview, menus);
        //设置适配器
        recyclerView.setAdapter(menusRecyclerViewAdapter);

        //https://www.jb51.net/article/181536.htm
        //滑动指示器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //整体的总宽度，注意是整体，包括在显示区域之外的
                //滚动条表示的总范围
                int temp = recyclerView.computeHorizontalScrollRange();
                //滑块的偏移量
                int offset = recyclerView.computeHorizontalScrollOffset();
                //可视区域长度
                int extent = recyclerView.computeHorizontalScrollExtent();
                //滑出部分在剩余范围的比例
                float proportion = (float) (offset * 1.0 / (temp - extent));
                //计算滚动条宽度
                float transMaxRange = parent_layout.getWidth() - lineIndicator.getWidth();
                //设置滚动条移动
                lineIndicator.setTranslationX(transMaxRange * proportion);
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.divider) .navigationBarColor(R.color.white) .fitsSystemWindows(true).init();
    }
}

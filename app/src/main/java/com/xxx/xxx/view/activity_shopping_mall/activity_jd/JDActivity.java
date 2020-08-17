package com.xxx.xxx.view.activity_shopping_mall.activity_jd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.xxx.xxx.R;
import com.xxx.xxx.widget.AmountView;
import com.xxx.xxx.widget.page_menu.BannerIndicator;
import com.xxx.xxx.widget.page_menu.CircleIndicator;
import com.xxx.xxx.widget.page_menu.PageMenuLayout;
import com.xxx.xxx.widget.page_menu.holder.AbstractHolder;
import com.xxx.xxx.widget.page_menu.holder.PageMenuViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 xzb
 * @描述: 京东 首页  滑动菜单    https://blog.csdn.net/shenggaofei/article/details/102767959
 * https://github.com/xiaohaibin/PageMenuLayout
 * @创建时间 :2020/3/28 17:19
 */
public class JDActivity extends Activity {

    @BindView(R.id.pagemenu)
    PageMenuLayout<Integer> pageMenuLayout;

    @BindView(R.id.ci_indicator)
    CircleIndicator circleIndicator;

    @BindView(R.id.indicator)
    BannerIndicator bannerIndicator;

    @BindView(R.id.amount_view)
    AmountView amountView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd);
        ButterKnife.bind(this);

        List<Integer> list = new ArrayList<Integer>();
        list.add(R.mipmap.wazi);
        list.add(R.mipmap.kucha);
        list.add(R.mipmap.kuzi);
        list.add(R.mipmap.qunzi);
        list.add(R.mipmap.lianmaoweiyi);
        list.add(R.mipmap.bao);
        list.add(R.mipmap.xie);
        list.add(R.mipmap.yurongfu);
        list.add(R.mipmap.neiyi);
        list.add(R.mipmap.maozi);
        list.add(R.mipmap.weijin);
        list.add(R.mipmap.fengyi);
        list.add(R.mipmap.kucha);
        list.add(R.mipmap.weiyi);

        pageMenuLayout.setPageDatas(list, new PageMenuViewHolderCreator() {
            @Override
            public AbstractHolder createHolder(View itemView) {
                return new AbstractHolder<Integer>(itemView) {
                    private ImageView image_view;
                    @Override
                    protected void initView(View itemView) {
                        image_view = itemView.findViewById(R.id.image_view);
                    }

                    @Override
                    public void bindView(RecyclerView.ViewHolder holder, final Integer data, int pos) {
                        Glide.with(JDActivity.this)
                                .load(data)
                                .into(image_view);
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(JDActivity.this, data, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_jd_activity_recyclerview;
            }
        });
        pageMenuLayout.setOnPageListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }
        });
        circleIndicator.setViewPager(pageMenuLayout.getViewPager());
        bannerIndicator.setUpWidthViewPager(pageMenuLayout.getViewPager());

        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

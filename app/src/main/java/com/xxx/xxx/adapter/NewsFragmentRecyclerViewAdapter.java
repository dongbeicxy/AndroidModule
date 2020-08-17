package com.xxx.xxx.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xxx.xxx.R;
import com.xxx.xxx.bean.News360Bean;

import java.util.List;

/**
 * @作者 xzb
 * @描述:
 * @创建时间 :2020/6/8 13:59
 */
public class NewsFragmentRecyclerViewAdapter extends BaseMultiItemQuickAdapter<News360Bean.DataBean.ResBean, BaseViewHolder> {

    public NewsFragmentRecyclerViewAdapter(List<News360Bean.DataBean.ResBean> resBeans) {
        super(resBeans);
        addItemType(-1, R.layout.item_news_fragment_news_no_pic);
        addItemType(0, R.layout.item_news_fragment_news_no_pic);
        addItemType(1, R.layout.item_news_fragment_news_one_pic);
        addItemType(2, R.layout.item_news_fragment_news_one_pic);
        addItemType(3, R.layout.item_news_fragment_news_three_pic);
    }

    @Override
    protected void convert(BaseViewHolder helper, News360Bean.DataBean.ResBean item) {
        switch (helper.getItemViewType()) {
            case -1:
            case 0:
                helper.setText(R.id.title_tv, item.getT());
                helper.setText(R.id.src_tv, item.getF());
                break;
            case 1:
                helper.setText(R.id.title_tv, item.getT());
                break;
            case 2:
                //helper.setImageUrl(R.id.iv, item.getContent());
                break;
            case 3:
                //helper.setImageUrl(R.id.iv, item.getContent());
                break;
        }
    }
}

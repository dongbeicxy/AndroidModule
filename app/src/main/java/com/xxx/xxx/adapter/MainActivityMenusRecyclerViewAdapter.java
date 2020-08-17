package com.xxx.xxx.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xxx.xxx.R;

import java.util.List;

/**
 * 首页 功能模块 菜单 适配器
 */
public class MainActivityMenusRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MainActivityMenusRecyclerViewAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        //赋值
        baseViewHolder.setText(R.id.textView, s);
    }
}

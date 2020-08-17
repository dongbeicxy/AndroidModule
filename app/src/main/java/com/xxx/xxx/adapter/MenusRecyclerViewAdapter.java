package com.xxx.xxx.adapter;

import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xxx.xxx.R;
import com.xxx.xxx.bean.HomePagerMenus;

import java.util.List;

/**
 * 拼多多 关注 分类滑动列表 适配器
 */
public class MenusRecyclerViewAdapter extends BaseQuickAdapter<HomePagerMenus, BaseViewHolder> {

    private Fragment fragment;

    public MenusRecyclerViewAdapter(Fragment fragment, int layoutResId, List<HomePagerMenus> data) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomePagerMenus homePagerMenus) {
        //赋值
        baseViewHolder.setText(R.id.textView, homePagerMenus.getName());
        Glide.with(fragment)
                .load(homePagerMenus.getImageRes())
                .into((ImageView) baseViewHolder.getView(R.id.image_view));
    }
}

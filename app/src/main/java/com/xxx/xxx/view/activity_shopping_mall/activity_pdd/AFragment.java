package com.xxx.xxx.view.activity_shopping_mall.activity_pdd;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AFragment extends Fragment {
    private static final String ARG_C = "content";

    public static AFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        AFragment fragment = new AFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String content = getArguments().getString(ARG_C);
        TextView textView = new TextView(getContext());
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setText(String.format("Test\n\n%s", content));
        textView.setBackgroundColor(0xFFececec);
        Log.e("AFragment", "第" + content + "页创建了");
        return textView;
    }

    //当Fragment中的视图被移除时调用
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        String content = getArguments().getString(ARG_C);
        Log.e("AFragment", "第" + content + "页销毁了");
    }
}

package com.beicai.yyvoice.adapter;

import android.content.Context;

import com.beicai.yyvoice.viewholder.CommonViewHolder;

import java.util.List;

/**
 * Created by Yang on 2017/4/13.
 */

public class BannerAdapter extends MultiTypeBaseAdapter<String> {
    public BannerAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(CommonViewHolder holder, String data, int viewType) {

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return 0;
    }

    @Override
    protected int getViewType(int position, String data) {
        return 0;
    }
}

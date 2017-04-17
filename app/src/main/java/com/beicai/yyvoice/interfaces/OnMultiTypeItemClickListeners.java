package com.beicai.yyvoice.interfaces;

import com.beicai.yyvoice.viewholder.CommonViewHolder;

/**
 * Created by Yang on 2017/4/13.
 */

public interface OnMultiTypeItemClickListeners<T> {
    void onItemClick(CommonViewHolder viewHolder, T data, int position, int viewType);
}

package com.beicai.yyvoice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.beicai.yyvoice.R;

/**
 * Created by Yanger on 2017/4/17.
 */

public class AudienceAdapter extends android.widget.BaseAdapter{
    private Context mContext;

    public AudienceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return View.inflate(mContext, R.layout.item_audienceadapter, null);
    }
}

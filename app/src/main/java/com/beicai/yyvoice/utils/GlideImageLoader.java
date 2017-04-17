package com.beicai.yyvoice.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Yang on 2017/4/13.
 */

public class GlideImageLoader extends com.youth.banner.loader.ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}

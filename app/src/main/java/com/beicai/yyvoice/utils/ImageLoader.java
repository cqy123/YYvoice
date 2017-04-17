package com.beicai.yyvoice.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @Desc ImageLoader
 * @Date 2016/10/24
 **/
public class ImageLoader {
    public static void loadImage(Context context, Object path, ImageView imageView){
        Glide.with(context).load(path).into(imageView);
    }
}

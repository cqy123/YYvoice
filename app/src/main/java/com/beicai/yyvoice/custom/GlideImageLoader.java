package com.beicai.yyvoice.custom;


import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Yang on 2017/4/13.
 */

public class GlideImageLoader extends ImageLoader {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context).load((String) path).into(imageView);
    }
}

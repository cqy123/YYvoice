package com.beicai.yyvoice.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.beicai.yyvoice.R;
import com.beicai.yyvoice.bean.LiveEntity;
import com.beicai.yyvoice.custom.GlideCircleTransform;
import com.beicai.yyvoice.utils.GlideImageLoader;
import com.beicai.yyvoice.viewholder.CommonViewHolder;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/4/13.
 */

public class LoadMoreAdapter extends MultiTypeBaseAdapter<LiveEntity.ResultBean.ListBean> {
    private static final String POST_URL = "http://121.42.26.175:14444/";
    private Context mContext;
    private List<String> images;
    private Banner banner;

    public LoadMoreAdapter(Context context, List<LiveEntity.ResultBean.ListBean> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        mContext=context;
    }

    @Override
    protected void convert(CommonViewHolder holder, final LiveEntity.ResultBean.ListBean data, int viewType) {
        if (viewType == 0) {
            initData();
            banner = (Banner) holder.getConvertView().findViewById(R.id.banner);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(images);
            //设置banner动画效果
            banner.setBannerAnimation(Transformer.DepthPage);
            //设置自动轮播，默认为true
            banner.isAutoPlay(true);
            //设置轮播时间
            banner.setDelayTime(1500);
            //设置指示器位置（当banner模式中有指示器时）
            banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        } else {
            ImageView avator  = (ImageView) holder.getConvertView().findViewById(R.id.avator);
            ImageView pic  = (ImageView) holder.getConvertView().findViewById(R.id.pic);
            TextView name = (TextView) holder.getConvertView().findViewById(R.id.name);
            name.setText(data.getData().getLive_name());
//            holder.setText(R.id.name,data.getData().getLive_name());//这是一种写法
            Glide.with(mContext).load(data.getUser().getUser_data().getAvatar()).transform(new GlideCircleTransform(mContext)).into(avator);//// TODO: 17/4/14 不好使
            Glide.with(mContext).load(data.getData().getPic()).into(pic);

        }
    }

    private void initData() {
        String[] array = mContext.getResources().getStringArray(R.array.url);
        images = new ArrayList<>();
        for (int i=0;i<array.length;i++){
            images.add(array[i]);
        }
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == 0) {

                
            return R.layout.recy_item_1;
        }

        return R.layout.item_layout1;
    }

    @Override
    protected int getViewType(int position, LiveEntity.ResultBean.ListBean data) {

        if (data == null) {
            return 0;
        } else {
            return 1;
        }

    }
}

package com.beicai.yyvoice.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.beicai.yyvoice.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by Yang on 2017/4/11.
 */

public class MyApplication extends Application {
    public static List<?> images=new ArrayList<>();
    // 保存项目中所有的activity
    private static ArrayList<Activity> list = new ArrayList<Activity>();

    // 销毁所有的activity
    public static void closeActivity() {
        for (Activity activity : list) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        String[] urls = getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        initImageLoader(this);

    }

    public void initImageLoaderCache() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), "ImageLoader/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .memoryCacheExtraOptions(720, 1280)
                // default = device screen dimensions
                .diskCacheExtraOptions(720, 1280, null)
                .memoryCache(new WeakMemoryCache())
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13)
                // default
                .diskCache(new UnlimitedDiskCache(cacheDir))
                // default
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // default
                .imageDownloader(
                        new BaseImageDownloader(getApplicationContext())) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .build();
        ImageLoader.getInstance().init(config);
    }

    // 添加actiivty
    public void addActivity(Activity activity) {
        list.add(activity);
    }

    // 移除activity
    public void removeActivity(Activity activity) {
        list.remove(activity);
    }

    private void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // 缓存路径
                .diskCache(new UnlimitedDiskCache(cacheDir)).writeDebugLogs()
                // connectTimeout (10 s), readTimeout (10 s)  超时时间
                .imageDownloader(new BaseImageDownloader(context, 10 * 1000, 10 * 1000))
                // 缓存的文件数量
                .diskCacheFileCount(100)
                .build();
        ImageLoader.getInstance().init(config);
    }
}

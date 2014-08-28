package com.mindpin.kc_android.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.mindpin.kc_android.utils.BaseUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by dd on 14-8-1.
 */
public class KCApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
//        ImageLoaderConfiguration config =
//                new ImageLoaderConfiguration.Builder(getApplicationContext()).build();

        DisplayImageOptions options;
        ImageLoaderConfiguration config;

        File cache_dir = BaseUtils.create_dir(new File("/kc/cache/image"));

        if (cache_dir != null) {
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(false)
                    .cacheOnDisk(true)
                    .build();

            config = new ImageLoaderConfiguration.Builder(getApplicationContext())

                    // 设置缓存图片的宽度跟高度
                    .memoryCacheExtraOptions(480, 800)
                    .diskCacheExtraOptions(480, 800, null)

                    // 通过 LruMemoryCache 实现缓存机制
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(2 * 1024 * 1024)

                            // 限制缓存文件数量百分比
                    .memoryCacheSizePercentage(13)

                    .diskCache(new UnlimitedDiscCache(cache_dir))
                    .diskCacheSize(50 * 1024 * 1024)

                    // 硬盘缓存文件数量
                    .diskCacheFileCount(100)
                    .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                    .defaultDisplayImageOptions(options)


                    .build();

            ImageLoader.getInstance().init(config);
        }


        context = this.getApplicationContext();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public static Context get_context(){
        return context;
    }




}

package com.mindpin.kc_android.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
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

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(true)

                .build();


        File cache_dir = create_dir(new File("/topic_images_cache"));
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            .memoryCacheExtraOptions(480, 800)
            .diskCacheExtraOptions(480, 800, null)
            .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
            .memoryCacheSize(2 * 1024 * 1024)
            .memoryCacheSizePercentage(13)
            .diskCache(new UnlimitedDiscCache(cache_dir)) // default
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileCount(100)
            .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
            .defaultDisplayImageOptions(options)


            .build();


        ImageLoader.getInstance().init(config);

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

    private File create_dir(File file_dir) {
        file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + file_dir.getPath());
        if (file_dir.exists()) {
            Log.i("目录已经存在 ", file_dir.getAbsolutePath());
            return file_dir;
        }

        Log.i("目录不存在 开始创建目录 ", "true");

        try{
            boolean result = file_dir.mkdirs();
            if (result) {
                Log.i("目录创建成功 ", "true");
                return file_dir;
            }
        } catch(SecurityException se){
            Log.i("目录创建失败 ", se.toString());
        }

        return null;
    }


}

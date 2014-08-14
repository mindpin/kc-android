package com.mindpin.kc_android.application;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by dd on 14-8-1.
 */
public class KCApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
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
}

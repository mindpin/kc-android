package com.mindpin.kc_android;

import android.app.Application;
import com.activeandroid.ActiveAndroid;

/**
 * Created by dd on 14-8-1.
 */
public class KCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}

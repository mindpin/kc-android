package com.mindpin.kc_android.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mindpin.kc_android.network.HttpApi;

import roboguice.util.RoboAsyncTask;

/**
 * Created by fushang318 on 2014/8/13.
 */
public abstract class KCAsyncTask<ResultT> extends RoboAsyncTask<ResultT> {

    protected KCAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected void onException(Exception e) throws RuntimeException {

        if(e.getClass() == HttpApi.RequestDataErrorException.class){
            Toast.makeText(getContext(), "服务器数据错误，请稍后再试", Toast.LENGTH_SHORT).show();
        }

        if(e.getClass() == HttpApi.AuthErrorException.class){
            Toast.makeText(getContext(), "用户认证失败，请重新登陆", Toast.LENGTH_SHORT).show();
        }

        if(e.getClass() == HttpApi.NetworkErrorException.class){
            Toast.makeText(getContext(), "网络连接失败，请稍后再试", Toast.LENGTH_SHORT).show();
        }

        e.printStackTrace();
    }
}

package com.mindpin.kc_android.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.mindpin.kc_android.R;

/**
 * Created by fushang318 on 2014/8/4.
 */
public class NetworkUtils {
//    public static boolean net_is_active(Context context){
//        ConnectivityManager mgrConn = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = mgrConn.getActiveNetworkInfo();
//        return (info != null && info.isAvailable());
//    }
//
//    public static boolean is_wifi_active(Context context) {
//        ConnectivityManager mgrConn = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        TelephonyManager mgrTel = (TelephonyManager) context
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
//                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
//                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
//    }

    /**
     * 检测网络是否连接
     * @return
     */
    public static boolean check_network_state(Context context) {
        boolean available = false;
        //得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            available = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!available) {
            set_network(context);
        } else {
            is_network_available(manager);
        }

        return available;
    }

    /**
     * 网络已经连接，然后去判断是wifi连接还是GPRS连接
     * 设置一些自己的逻辑调用
     */
    private static void is_network_available(ConnectivityManager manager) {
        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING){
            Log.d("log","gprs is open");
        }
        if(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING){
            Log.d("log","wifi is open");
        }
    }

    /**
     * 网络未连接时，调用设置方法
     */
    private static void set_network(Context context){
        final Context _context = context;
        Toast.makeText(_context, "wifi 没有打开", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                _context.startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }


}

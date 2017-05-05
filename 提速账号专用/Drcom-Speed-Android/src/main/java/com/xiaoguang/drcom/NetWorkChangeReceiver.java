package com.xiaoguang.drcom;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by xiaoguang on 2016/8/28.
 */
public class NetWorkChangeReceiver extends BroadcastReceiver {
    private final String tag = "WIFI链接状况";
    private final String aims[] = {"Young","gdufe","gdufe-teacher","Drcom","Dr.com"};

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.CONNECTED)) {

                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String wifiName = wifiInfo.getSSID();   //获取的是带引号的如"Young"
                if(TextUtils.isEmpty(wifiName)) return;

                wifiName = wifiName.split("\"")[1]; //去引号
//                Log.e(tag, "连接到WIFI " + wifiName);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < aims.length;i++){
                    if(wifiName.equalsIgnoreCase(aims[i])){
//                        Log.e(tag, "登陆~ " + wifiName);
                        Toast.makeText(context.getApplicationContext(), "登陆 "+aims[i], Toast.LENGTH_SHORT).show();

                        SharedPreferences sp = context.getSharedPreferences(MainActivity.PREFERENCES_NAME, Activity.MODE_PRIVATE);
                        String str_acc = sp.getString("account", "");
                        String str_psw = sp.getString("password", "");
                        if(TextUtils.isEmpty(str_acc)||TextUtils.isEmpty(str_psw)){
                            Toast.makeText(context.getApplicationContext(), "账号或密码为空", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        DrcomAdmin drcomAdmin = DrcomAdmin.getInstance();
                        drcomAdmin.initAccountInfo(str_acc, str_psw);
                        drcomAdmin.login();
                        break;
                    }
                }
                // 当前WIFI名称

            }
        }
    }
}

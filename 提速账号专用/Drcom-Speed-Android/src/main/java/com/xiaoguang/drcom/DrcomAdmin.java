package com.xiaoguang.drcom;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaoguang on 2016/8/28.
 */
public class DrcomAdmin {
    private static DrcomAdmin instance = new DrcomAdmin();
    private static String url = "http://58.62.247.115";
    private static String TIPS_LOGIN_SUCCESS = "登陆成功";
    private static String TIPS_LOGOUT_SUCCESS = "注销成功";
    private static String TIPS_LOGIN_FAIL = "登陆失败";
    private static String TIPS_LOGOUT_FAIL = "注销失败";
    private static String TIPS_NO_CT = "账号或密码错误，或者非电信号";

    private Context context;
    private RequestQueue requestQueue ;
    private String sno,password;

    private DrcomAdmin(){}
    public static DrcomAdmin getInstance(){
        return instance;
    }

    public void initContext(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public void initAccountInfo(String sno,String password){
        this.sno = sno;
        this.password = password;
    }

    public void login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(checkRes(response).equalsIgnoreCase(TIPS_LOGIN_FAIL)){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            login();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(),"非账号原因"+TIPS_LOGIN_FAIL+"，多为网络不佳",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("DDDDD", sno);
                map.put("upass", password);
                map.put("0MKKey", "%25B5%25C7%25C2%25BC%2BLogin");
                map.put("Submit", "%E7%99%BB%E9%99%86");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void logout() {
        String logoutUrl = url+"/F.htm?Submit2=%E9%80%80%E5%87%BA";
        StringRequest stringRequest = new StringRequest(logoutUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,TIPS_LOGOUT_SUCCESS,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(), TIPS_LOGOUT_FAIL, Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(stringRequest);
        if(MainActivity.getAutoExitState()){
            ((Activity)context).finish();
        }
    }

    private String checkRes(String response){
        String res = "error";
        Pattern pattern = Pattern.compile("Msg=(\\d*);");
        Matcher matcher = pattern.matcher(response);
        boolean success = true;
        while (matcher.find()) {
            success = false;
            String id = matcher.group(1);
            switch (Integer.parseInt(id)) {
                case 14:
                    res = TIPS_LOGIN_FAIL;
                    break;
                case 15:
                    res = TIPS_LOGIN_SUCCESS;
                    break;
                case 1:
                    res = TIPS_NO_CT;
                    break;
            }
        }
        boolean beExit = false;
        if(success){
            res = TIPS_LOGIN_SUCCESS;
            beExit = true;
        }
        Toast.makeText(context.getApplicationContext(),res,Toast.LENGTH_SHORT).show();

        if(beExit && MainActivity.getAutoExitState()){
            ((Activity)context).finish();
        }
        return res;
    }
}

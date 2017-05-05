package com.xiaoguang.drcom;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btn_login,btn_logout,btn_forgot,btn_save;
    private EditText ed_acc,ed_psw;
    private CheckBox cb_hide_info,cb_auto_exit;
    private TextView tv_acc,tv_psw;

    public final static String PREFERENCES_NAME = "data";

    private static boolean bool_autoExit = false;
    private boolean bool_hideInfo = false;

    DrcomAdmin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Dr.com电信版v1.2.0");

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_forgot = (Button)findViewById(R.id.btn_forgot);
        btn_save = (Button)findViewById(R.id.btn_save);
        cb_auto_exit = (CheckBox)findViewById(R.id.cb_auto_exit);
        cb_hide_info = (CheckBox)findViewById(R.id.cb_hide_info);

        ed_acc = (EditText)findViewById(R.id.ed_account);
        ed_psw = (EditText)findViewById(R.id.ed_psw);
        tv_acc = (TextView)findViewById(R.id.tv_acc);
        tv_psw = (TextView)findViewById(R.id.tv_psw);
        btn_login.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_forgot.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        cb_hide_info.setOnClickListener(this);
        cb_auto_exit.setOnClickListener(this);
        cb_auto_exit.setChecked(true);

        admin = DrcomAdmin.getInstance();
        admin.initContext(this);

        SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        String str_acc = sp.getString("account", "");
        String str_psw = sp.getString("password", "");
        bool_hideInfo = sp.getBoolean("hideInfo", false);
        bool_autoExit = sp.getBoolean("autoExit", true);
        if(str_acc != "" && str_psw != ""){
            ed_acc.setText(str_acc);
            ed_psw.setText(str_psw);
            admin.initAccountInfo(str_acc,str_psw);
        }
        cb_hide_info.setChecked(bool_hideInfo);
        cb_auto_exit.setChecked(bool_autoExit);
        if(cb_hide_info.isChecked()){
            hideInfo();
        }
    }

    private void hideInfo(){
        if(tv_acc.getVisibility() == View.GONE){
            ed_acc.setVisibility(View.VISIBLE);
            ed_psw.setVisibility(View.VISIBLE);
            tv_acc.setVisibility(View.VISIBLE);
            tv_psw.setVisibility(View.VISIBLE);
        }else{
            ed_acc.setVisibility(View.GONE);
            ed_psw.setVisibility(View.GONE);
            tv_acc.setVisibility(View.GONE);
            tv_psw.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View view) {
        SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        admin.initAccountInfo(ed_acc.getText().toString(),ed_psw.getText().toString());
        switch (view.getId()){
            case R.id.btn_login:
                admin.login();
                break;
            case R.id.btn_logout:
                admin.logout();
                break;
            case R.id.btn_forgot:
                forgotPsw();
                break;
            case R.id.cb_hide_info:
                hideInfo();
                editor.putBoolean("hideInfo", cb_hide_info.isChecked());
                break;
            case R.id.cb_auto_exit:
                editor.putBoolean("autoExit", cb_auto_exit.isChecked());
                break;
            case R.id.btn_save:
                if(TextUtils.isEmpty(ed_acc.getText().toString())||
                        TextUtils.isEmpty(ed_psw.getText().toString())){
                    Toast.makeText(this, "学号密码呢？", Toast.LENGTH_SHORT).show();
                    return;
                }
                editor.putString("account", ed_acc.getText().toString());
                editor.putString("password", ed_psw.getText().toString());
                Toast.makeText(this, "保存成功 退出即可", Toast.LENGTH_SHORT).show();
                break;
        }
        editor.commit();
    }

    private void forgotPsw() {
        SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        ed_acc.setText("");
        ed_psw.setText("");
        Toast.makeText(MainActivity.this, "已清除缓存", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Toast.makeText(this,"小光:https://github.com/wintercoder",Toast.LENGTH_LONG).show();
        }else if(id == R.id.action_usage){
            Toast.makeText(this,"黑科技系列：仅电信号可用，可多登陆",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    public static boolean getAutoExitState(){
        return bool_autoExit;
    }
}

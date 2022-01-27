package com.meyok.c3_logininterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        if (sp != null){
            //第一个参数key，第二个默认值
            String name = sp.getString("name", "");
            String password = sp.getString("password", "");
            if (!name.equals("") && !password.equals("")) {
                jumpToLogin(3000, MainActivity.class);
            }else{
                jumpToLogin(3000, LoginActivity.class);
            }
        }
    }

    private void jumpToLogin(long time, final Class<?> cls) {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, cls);
                startActivity(intent);
                finish();
            }
        }, time);
    }
}
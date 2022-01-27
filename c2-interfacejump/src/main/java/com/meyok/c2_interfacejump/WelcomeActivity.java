package com.meyok.c2_interfacejump;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    ImageView mIvGoto;
    TextView mTvShowTime;
    static int WELCOME_CODE = 1;
    static String TAG = "WELCOME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        1.延迟跳转
        /*jumpToLogin(5000, MainActivity.class);*/

//        2.倒计时跳转
        /*mTvShowTime = findViewById(R.id.tv_showtime);
        showTime();*/

//        3.跳转界面，并从新界面获得值返回界面
        mIvGoto = findViewById(R.id.iv_goto);
        mIvGoto.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(WelcomeActivity.this, MainActivity.class);
            startActivityForResult(intent, WELCOME_CODE);
        });


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

    private void showTime() {
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {
                mTvShowTime.setText("请稍等"+(l/1000)+"秒后跳转");
            }

            @Override
            public void onFinish() {
                mTvShowTime.setText("努力跳转中……");
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        1)无返回数据
        if(resultCode == MainActivity.FAILED){
            Toast.makeText(WelcomeActivity.this, "NO DATA", Toast.LENGTH_LONG).show();
        }

//        2)有返回数据
        if(resultCode == MainActivity.SUCCESS){
            if(data != null){
                String name = data.getStringExtra("name");
                Toast.makeText(WelcomeActivity.this, name, Toast.LENGTH_LONG).show();
            }
        }

    }



//    4.Activity周期
/*
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");

    }*/
}
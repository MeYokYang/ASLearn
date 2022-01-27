package com.meyok.c2_interfacejump;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mBtnClick;
    public static int SUCCESS = 0;
    public static int FAILED = 1;

    static String testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            testText = savedInstanceState.getString("name");
            Toast.makeText(MainActivity.this, testText, Toast.LENGTH_LONG).show();
        }


        //页面跳转带返回值
        mBtnClick = findViewById(R.id.btn);
        mBtnClick.setOnClickListener(new View.OnClickListener() {

            //无返回数据
            @Override
            public void onClick(View view) {
                setResult(FAILED);
                finish();
            }

            //有返回数据
/*            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", "meyok");
                setResult(SUCCESS, intent);
                finish();
            }*/
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", "kgc");
    }

}
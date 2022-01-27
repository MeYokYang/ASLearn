package com.meyok.c6_sideslipmenu.drawer_layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.meyok.c6_sideslipmenu.R;

public class DrawerLayoutActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        mDrawerLayout = findViewById(R.id.drawer);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            //滑动时回调
            //v-->打开的百分比，0.0f-1.0f
            @Override
            public void onDrawerSlide(View view, float v) {

            }

            //完全打开后回调
            @Override
            public void onDrawerOpened(View view) {

            }

            //完全关闭后回调
            @Override
            public void onDrawerClosed(View view) {

            }

            //状态改变时回调
            //0.1.2
            //STATE_IDLE=0闲置 STATE_DRAGGING=1拖拽 STATE_SETTING=2固定
            @Override
            public void onDrawerStateChanged(int i) {

            }
        });


    }

    public void openMenu(View view){
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

}
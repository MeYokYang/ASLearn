package com.meyok.c6_sideslipmenu.sliding_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.meyok.c6_sideslipmenu.R;
import com.meyok.c6_sideslipmenu.customers.slidingmenu.SlidingMenu;
import com.meyok.c6_sideslipmenu.drawer_layout.LeftMenuFragment;

public class SlidingMenuActivity extends android.support.v4.app.FragmentActivity {


    SlidingMenu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);

        initMenu();

    }

    private void initMenu() {


        menu = new SlidingMenu(this);
        //设置菜单布局
        menu.setMenu(R.layout.fragment_left_menu);
        //设置滑出模式
        //LEFT->左侧,RIGHT->右侧,LEFT_RIGHT->左右侧
        menu.setMode(SlidingMenu.LEFT);
        //触摸模式
        //TOUCHMODE_FULLSCREEN->全屏,TOUCHMODE_MARGIN->侧边,TOUCHMODE_NONE->不能
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //菜单拉出后最终与边的距离
        menu.setBehindOffsetRes(R.dimen.menu_offset);
        //将menu与activity关联
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置渐变0.0f~1.0f,值越大越暗
        menu.setFadeDegree(0.35f);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
//        transaction.replace(R.id.content, leftMenuFragment);
//        transaction.commit();
//
//        menu.setMode(SlidingMenu.LEFT_RIGHT);
//        menu.setSecondaryMenu(R.layout.fragment_right_menu);
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        //菜单拉出后最终与边的距离
//        menu.setBehindOffsetRes(R.dimen.menu_offset);
//        //将Menu与Activity关联
//        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//        //设置渐变
//        menu.setFadeDegree(0.35f);


//        TextView tvLeftText = menu.findViewById(R.id.tv_left_text);
//        tvLeftText.setText("Hello World!");
//
//        TextView tvRightText = menu.getSecondaryMenu().findViewById(R.id.tv_right_text);
//        tvRightText.setText("Hello World!");

    }

    public void openMenu(View view){
//        menu.isMenuShowing();
//        //显示菜单
//        menu.showMenu();
//        //隐藏菜单
//        menu.showContent();

        menu.toggle();

        menu.showSecondaryMenu();


    }
}
package com.meyok.c4_maininterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.meyok.c4_maininterface.fragments.CollectionFragment;
import com.meyok.c4_maininterface.fragments.IndexFragment;
import com.meyok.c4_maininterface.fragments.RecommendationFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton mBtnIndex, mBtnRecommend, mBtnCollection;
    Fragment[] fragments = new Fragment[3];
    Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();

        showFragment(R.id.btn_index);

    }

    private void initView() {
        mBtnIndex = findViewById(R.id.btn_index);
        mBtnRecommend = findViewById(R.id.btn_recommend);
        mBtnCollection = findViewById(R.id.btn_collection);
    }


    private void initEvent() {
        mBtnIndex.setOnClickListener(this);
        mBtnRecommend.setOnClickListener(this);
        mBtnCollection.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        showFragment(view.getId());
    }

    private void showFragment(int id) {
        //获取FragmentManager对象
        FragmentManager fragmentManager = getSupportFragmentManager();

        //开启事务，获取FragmentTransaction对象
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;
        switch (id){
            case R.id.btn_index:
                //添加Fragment
                if (fragments[0] != null){
                    fragment = fragments[0];
                }else{
                    fragment = new IndexFragment();
                    fragments[0] = fragment;
                }
                break;
            case R.id.btn_recommend:
                if (fragments[1] != null){
                    fragment = fragments[1];
                }else{
                    fragment = new RecommendationFragment();
                    fragments[1] = fragment;
                }
                break;
            case R.id.btn_collection:
                if (fragments[2] != null){
                    fragment = fragments[2];
                }else{
                    fragment = new CollectionFragment();
                    fragments[2] = fragment;
                }
                break;
        }

        if (mCurrentFragment != null){
            fragmentTransaction.hide(mCurrentFragment);
        }

        if(!fragment.isAdded()){
            fragmentTransaction.add(R.id.content, fragment);
        }else{
            fragmentTransaction.show(fragment);
        }

//        fragmentTransaction.replace(R.id.content, fragment);

        fragmentTransaction.commit();

        mCurrentFragment = fragment;
    }
}
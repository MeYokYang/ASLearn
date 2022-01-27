package com.meyok.c5_filminterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.meyok.c5_filminterface.fragments.HotFilmFragment;
import com.meyok.c5_filminterface.fragments.SoonFilmFragment;

public class FilmActivity extends AppCompatActivity implements View.OnClickListener{

    RadioButton mHot, mSoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        initView();

    }

    private void initView() {
        mHot = findViewById(R.id.rBtn_hot);
        mHot.setChecked(true);
        mSoon = findViewById(R.id.rBtn_soon);

        mHot.setOnClickListener(this);
        mSoon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        showFragment(view.getId());
    }

    public void showFragment(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;
        switch (id) {
            case R.id.rBtn_hot:
                fragment = new HotFilmFragment();
                break;
            case R.id.rBtn_soon:
                fragment = new SoonFilmFragment();
                break;
        }

        fragmentTransaction.replace(R.id.film_fragment, fragment);
        fragmentTransaction.commit();

    }
}
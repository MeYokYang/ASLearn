package com.meyok.c5_filminterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.meyok.c5_filminterface.customers.MyTitleView;
import com.meyok.c5_filminterface.entities.Film;
import com.meyok.c5_filminterface.fragments.FilmIntroductionFragment;

public class FilmDetailActivity extends AppCompatActivity {

    MyTitleView myTitleView;

    FilmIntroductionFragment mIntroduceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        myTitleView = findViewById(R.id.customer_titleView);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("film");
        Film film = (Film) bundle.getSerializable("film");

        myTitleView.setText(film.getName());

        myTitleView.setMyOnclickListener(() -> Toast.makeText(FilmDetailActivity.this, "点击了右按钮", Toast.LENGTH_LONG).show());

        sendData(film, bundle);
    }

    private void sendData(Film film, Bundle bundle) {


        //静态赋值
        FragmentManager manager = getSupportFragmentManager();
        mIntroduceFragment = (FilmIntroductionFragment) manager.findFragmentById(R.id.fragment_film_introduce);
        mIntroduceFragment.setData(film);

        //动态赋值
        /*FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mDetailFragment = new FilmDetailFragment();
        mDetailFragment.setArguments(bundle);

        transaction.replace(R.id.content, mIntroduceFragment);
        transaction.commit();*/

    }
}
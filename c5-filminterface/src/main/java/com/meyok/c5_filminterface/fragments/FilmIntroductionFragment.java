package com.meyok.c5_filminterface.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meyok.c5_filminterface.R;
import com.meyok.c5_filminterface.entities.Film;


public class FilmIntroductionFragment extends Fragment {

    ImageView imageView;
    TextView tvName, tvDirector, tvPlayer, tvDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film_introduction, container, false);

        imageView = view.findViewById(R.id.iv_img);
        tvName = view.findViewById(R.id.tv_name);
        tvDirector = view.findViewById(R.id.tv_director);
        tvPlayer = view.findViewById(R.id.tv_player);
        tvDesc = view.findViewById(R.id.tv_desc);

        //动态赋值时需要
        /*Bundle bundle = getArguments();
        Film film = (Film) bundle.getSerializable("film");
        setData(film);*/

        return view;
    }

    public void setData(Film film){

        if(film != null){


            tvName.setText(film.getName());
            tvDirector.setText("导演："+"XXX");
            tvPlayer.setText("主演："+film.getPlayer());
            tvDesc.setText("简介："+film.getDesc());

        }
    }

}
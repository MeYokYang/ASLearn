package com.meyok.c4_maininterface.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.meyok.c4_maininterface.FilmActivity;
import com.meyok.c4_maininterface.R;

public class IndexFragment extends Fragment {

    ImageButton mBtn_film;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_index, container, false);
        mBtn_film = view.findViewById(R.id.iBtn_film);
        mBtn_film.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), FilmActivity.class);
            startActivity(intent);
        });
        return view;
    }
}
package com.meyok.c5_filminterface.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meyok.c5_filminterface.R;
import com.meyok.c5_filminterface.adapters.FilmPagesAdapter;
import com.meyok.c5_filminterface.entities.Film;
import com.meyok.c5_filminterface.entities.Result;
import com.meyok.c5_filminterface.pagetransformers.ZoomOutPageTransformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class BaseFilmFragment extends Fragment {

    ViewPager mPages;
    List<Film> list = new ArrayList<>();
    String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_film, container, false);

        getData();
        //获取ViewPager对象
        mPages = view.findViewById(R.id.vp_pages);

        //向其中添加数据

        return view;

    }

    private void getData(){
        if(url != null && url != ""){

            RequestParams params = new RequestParams(url);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Gson gson = new Gson();
                    Result<Film> filmResult = gson.fromJson(result, new TypeToken<Result<Film>>(){}.getType());
                    if (filmResult.getCode() == 0) {
                        list = filmResult.getList();
                        FilmPagesAdapter filmPagesAdapter = new FilmPagesAdapter(list, getActivity());
                        mPages.setAdapter(filmPagesAdapter);
                        mPages.setPageTransformer(true, new ZoomOutPageTransformer());
                    }

                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                }
                @Override
                public void onCancelled(CancelledException cex) {
                }
                @Override
                public void onFinished() {
                }
            });
        }

    }
}
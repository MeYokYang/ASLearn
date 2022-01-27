package com.meyok.c5_filminterface.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;


import com.meyok.c5_filminterface.FilmDetailActivity;
import com.meyok.c5_filminterface.R;
import com.meyok.c5_filminterface.entities.Film;

import org.xutils.x;

import java.util.List;

public class FilmPagesAdapter extends PagerAdapter {

    List<Film> list;
    Context context;

    public FilmPagesAdapter(List<Film> list, Context context){
        this.list = list;
        this.context = context;
    }

    //返回页卡数量
    @Override
    public int getCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    //判断View和Key是否相同,每一页有个Key
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    //将页卡添加到ViewPager中
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_page_viewpager_hot, null);

        Film film = list.get(position);

        //获取布局中全部的控件对象
        ImageView iv_img = view.findViewById(R.id.iv_img);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_grade = view.findViewById(R.id.tv_grade);
        TextView tv_director = view.findViewById(R.id.tv_director);
        TextView tv_player = view.findViewById(R.id.tv_player);
        TextView tv_type = view.findViewById(R.id.tv_type);
        TextView tv_area = view.findViewById(R.id.tv_area);
        TextView tv_duration = view.findViewById(R.id.tv_duration);

        iv_img.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, FilmDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("film", film);
            intent.putExtra("film", bundle);

            context.startActivity(intent);
        });

        String url = "";
        x.image().bind(iv_img, url);

        tv_name.setText(film.getName());
        tv_grade.setText("9.1");
        tv_director.setText("导演：克里斯托弗·诺兰");
        tv_player.setText("主演："+film.getPlayer());
        tv_type.setText("类型："+film.getType());
        tv_area.setText("地区：巴西");
        tv_duration.setText("时长："+film.getDuration());

        container.addView(view);
        return view;
    }

    //销毁页卡
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(list.get(position));
        container.removeView((View) object);
    }
}

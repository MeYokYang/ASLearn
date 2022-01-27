package com.meyok.c4_maininterface.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.meyok.c4_maininterface.R;
import com.meyok.c4_maininterface.entities.Film;

import java.util.HashMap;
import java.util.List;

public class CollectionAdapter extends BaseAdapter {

    List<Film> list;
    Context context;

    private boolean isEditMode = false;

    HashMap<Integer, Boolean> checkBoxStateController;

    public CollectionAdapter(List<Film> list, Context context) {
        this.list = list;
        this.context = context;

        checkBoxStateController = new HashMap<>();
        for(int i = 0; i < list.size(); ++i){
            checkBoxStateController.put(i, false);
        }

    }

    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            view = inflater.inflate(R.layout.item_collection_list, null);
            
            viewHolder.ivPhoto = view.findViewById(R.id.iv_photo);
            viewHolder.tvName = view.findViewById(R.id.tv_film_name);
            viewHolder.tvType = view.findViewById(R.id.tv_film_type);
            viewHolder.checkBox = view.findViewById(R.id.cb_check);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (isEditMode) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        }else{
            viewHolder.checkBox.setVisibility(View.GONE);
        }

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxStateController.remove(i);
                checkBoxStateController.put(i, b);
            }
        });

        Film film = list.get(i);

        viewHolder.ivPhoto.setImageResource(R.drawable.playbill);
        viewHolder.tvName.setText(film.getName());
        viewHolder.tvType.setText(film.getType());
        viewHolder.checkBox.setChecked(checkBoxStateController.get(i));

        return view;
    }

    public class ViewHolder{
        ImageView ivPhoto;
        TextView tvName, tvType;
        CheckBox checkBox;
    }

    public void setEditMode(boolean isEditMode){
        this.isEditMode = isEditMode;
    }

    public void setAllSelect(boolean selectOrCancel){
        checkBoxStateController.clear();
        for (int i = 0; i < list.size(); i++) {
            checkBoxStateController.put(i, selectOrCancel);
        }
    }

    public void deleteItem(){
        for (int i = list.size()-1; i >= 0; i--) {
            if (checkBoxStateController.get(i)) {
                list.remove(i);
                checkBoxStateController.remove(i);
            }
        }

        setAllSelect(false);

    }

    public boolean isChecked(){
        for (int i = 0; i < list.size(); i++) {
            if(checkBoxStateController.get(i)){
                return true;
            }
        }
        return false;
    }

}

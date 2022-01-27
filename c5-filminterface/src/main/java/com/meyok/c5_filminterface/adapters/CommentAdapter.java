package com.meyok.c5_filminterface.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meyok.c5_filminterface.R;
import com.meyok.c5_filminterface.entities.Comment;

import java.util.List;

public class CommentAdapter extends BaseAdapter {

    List<Comment> list;
    Context context;

    public CommentAdapter(Context context, List<Comment> list){
        this.list = list;
        this.context = context;
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
        if (list != null){
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;


        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_list_comment, null);

            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);
            viewHolder.tvContent = convertView.findViewById(R.id.tv_desc);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Comment comment = list.get(i);

        viewHolder.tvName.setText(comment.getName());
        viewHolder.tvTime.setText(comment.getTime());
        viewHolder.tvContent.setText(comment.getContent());

        return convertView;
    }

    public class ViewHolder{
        TextView tvName, tvTime, tvContent;
    }
}

package com.meyok.c5_filminterface.customers;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.meyok.c5_filminterface.R;

public class MyTitleView extends FrameLayout {

    Button leftButton, rightButton;
    TextView titleView;
    MyOnclickListener myOnclickListener;

    public MyTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleView);
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.MyTitleView_leftButtonBackground);
        leftButton.setBackground(backgroundDrawable);
        typedArray.recycle();

    }

    public void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.include_top_title, null);
        leftButton = view.findViewById(R.id.iBtn_left);
        rightButton = view.findViewById(R.id.iBtn_right);
        titleView = view.findViewById(R.id.iBtn_tv_title);

        leftButton.setOnClickListener(view1 -> ((Activity) context).finish());

        rightButton.setOnClickListener(view2 -> {
            if(myOnclickListener != null){
                myOnclickListener.onRightButtonClick();
            }
        });

        addView(view);
    }

    public void setMyOnclickListener(MyOnclickListener myOnclickListener) {
        this.myOnclickListener = myOnclickListener;
    }

    public void setText(String text){
        titleView.setText(text);
    }

    public interface MyOnclickListener{
        void onRightButtonClick();
    }
}

package com.meyok.c4_maininterface.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meyok.c4_maininterface.R;
import com.meyok.c4_maininterface.adapters.CollectionAdapter;
import com.meyok.c4_maininterface.entities.Film;
import com.meyok.c4_maininterface.entities.Result;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class CollectionFragment extends Fragment {

    TextView mTvText;
    ListView mLVCollections;
    List<Film> filmList;

    LinearLayout llEditModeMenu;
    Button mBtnAllSelect, mBtnCancel, mBtnDelete;

    CollectionAdapter collectionAdapter;

    public CollectionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        initView(view);
        initEvent();
        filmList = new ArrayList<>();
        getData();
        return view;
    }

    private void initView(View view) {
        mTvText = view.findViewById(R.id.tv_text);
        mLVCollections = view.findViewById(R.id.lv_collection);
        llEditModeMenu = view.findViewById(R.id.lv_bottom_edit);
        mBtnAllSelect = view.findViewById(R.id.btn_all_select);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mBtnDelete = view.findViewById(R.id.btn_delete);


    }

    private void initEvent(){
        mLVCollections.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                llEditModeMenu.setVisibility(View.VISIBLE);
                collectionAdapter.setEditMode(true);
                collectionAdapter.notifyDataSetChanged();

                return false;
            }
        });

        mBtnAllSelect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                collectionAdapter.setAllSelect(true);
                collectionAdapter.notifyDataSetChanged();
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                setCancelState();
                collectionAdapter.notifyDataSetChanged();
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(collectionAdapter.isChecked()){

                    new AlertDialog.Builder(getActivity())
                            .setTitle("是否删除？")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    collectionAdapter.deleteItem();
                                    setCancelState();
                                    collectionAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();


                }else{
                    Toast.makeText(getActivity(), "选呐", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setCancelState() {
        llEditModeMenu.setVisibility(View.GONE);
        collectionAdapter.setEditMode(false);
        collectionAdapter.setAllSelect(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(!hidden){
            mTvText.setText("重新加载！");
        }
    }


    private void getData(){

        String url = "";


        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                Result<Film> filmResult = gson.fromJson(result, new TypeToken<Result<Film>>(){}.getType());
                filmList = filmResult.getList();

                collectionAdapter = new CollectionAdapter(filmList, getContext());

                mLVCollections.setAdapter(collectionAdapter);


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
package com.meyok.c5_filminterface.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meyok.c5_filminterface.R;
import com.meyok.c5_filminterface.adapters.CommentAdapter;
import com.meyok.c5_filminterface.adapters.FilmPagesAdapter;
import com.meyok.c5_filminterface.customers.pulltorefresh.ILoadingLayout;
import com.meyok.c5_filminterface.customers.pulltorefresh.PullToRefreshBase;
import com.meyok.c5_filminterface.customers.pulltorefresh.PullToRefreshListView;
import com.meyok.c5_filminterface.entities.Comment;
import com.meyok.c5_filminterface.entities.Film;
import com.meyok.c5_filminterface.entities.Result;
import com.meyok.c5_filminterface.pagetransformers.ZoomOutPageTransformer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class FilmCommentFragment extends Fragment {

    PullToRefreshListView mLVCommendList;
    List<Comment> mCommendList;
    View nextBtn;

    CommentAdapter adapter;

    final int FIRST_LOAD = 0;
    final int NEXT_PAGE = 1;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film_comment, container, false);

        mLVCommendList =  view.findViewById(R.id.lv_commendList);
        mCommendList = new ArrayList<>();

        mLVCommendList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData(FIRST_LOAD);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData(NEXT_PAGE);
            }
        });

        getData(FIRST_LOAD);
        addEmptyView();
        addFooter();

        return view;
    }

    private void getData(int flag) {

        String url = "";
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();
                //TypeToken
                Result<Comment> commendResult = gson.fromJson(result, new TypeToken<Result<Comment>>(){}.getType());
                mCommendList.addAll(commendResult.getList());

//                if (commendResult.getList().size()<6){
//                    mLVCommendList.removeFooterView(nextBtn);
//                }

                ILoadingLayout layout = mLVCommendList.getLoadingLayoutProxy(true, false);
                layout.setLastUpdatedLabel("一小时前");
                layout.setPullLabel("拉，使劲拉！");
                layout.setReleaseLabel("疼，快松手！");
                layout.setRefreshingLabel("别着急，加着呢！");


                ILoadingLayout endLayout = mLVCommendList.getLoadingLayoutProxy(false, true);
                endLayout.setPullLabel("拉，使劲拉！");
                endLayout.setReleaseLabel("疼，快松手！");
                endLayout.setRefreshingLabel("别着急，加着呢！");

                if (flag == FIRST_LOAD){
                    adapter = new CommentAdapter(getActivity(), mCommendList);
                    mLVCommendList.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }

                mLVCommendList.onRefreshComplete();
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

    private void addEmptyView() {
        TextView emptyText = new TextView(getActivity());
        emptyText.setText("此影片展示没有评论！");
        emptyText.setGravity(Gravity.CENTER);
        emptyText.setVisibility(View.GONE);
        ((ViewGroup) mLVCommendList.getParent()).addView(emptyText);
        mLVCommendList.setEmptyView(emptyText);
    }

    private void addFooter() {
/*        nextBtn = new Button(getActivity());
        nextBtn.setText("下一页");*/

//        nextBtn = LayoutInflater.from(getActivity()).inflate(R.layout.footer_list_comment, null);
//
//
//        nextBtn.setOnClickListener(view -> getData(NEXT_PAGE));
//        mLVCommendList.addFooterView(nextBtn);



    }
}
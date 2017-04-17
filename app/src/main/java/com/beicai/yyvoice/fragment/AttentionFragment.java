package com.beicai.yyvoice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beicai.yyvoice.R;
import com.beicai.yyvoice.adapter.LoadMoreAdapter;
import com.beicai.yyvoice.bean.LiveEntity;
import com.beicai.yyvoice.interfaces.OnLoadMoreListener;
import com.beicai.yyvoice.interfaces.OnMultiTypeItemClickListeners;
import com.beicai.yyvoice.utils.Api;
import com.beicai.yyvoice.utils.ParamsUtils;
import com.beicai.yyvoice.viewholder.CommonViewHolder;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Yang on 2017/4/11.
 */

public class AttentionFragment extends Fragment {
    @BindView(R.id.can_content_view)
    RecyclerView can_content_view;
    @BindView(R.id.srl_layout)
    SwipeRefreshLayout srl_layout;
    private Context mContext;
    private View view;
    private LoadMoreAdapter mAdapter;
    private int page = 1;

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view=inflater.inflate(R.layout.fragmen_attention,null);
        ButterKnife.bind(this, this.view);
        initView();
        return this.view;
    }

    private void initView() {
        mAdapter = new LoadMoreAdapter(getActivity(), null, true);
        srl_layout.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorAccent));
        //下拉刷新
        srl_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }
        });
        //加载更多
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                page++;
                loadData();
            }
        });
        mAdapter.setOnMultiTypeItemClickListener(new OnMultiTypeItemClickListeners<LiveEntity.ResultBean.ListBean>() {
            @Override
            public void onItemClick(CommonViewHolder viewHolder, LiveEntity.ResultBean.ListBean data, int position, int viewType) {
              if (data ==null){
                    Toast.makeText(mContext,"我只是viewpager",Toast.LENGTH_LONG).show();
              }else {
                  LiveViewFragment liveViewFragment = new LiveViewFragment();
                  getFragmentManager().beginTransaction().add(R.id.frame_attention,liveViewFragment).commit();
                  new MainDialogFragment().show(getFragmentManager(),"MainDialogFragment");
              }

            }
        });
        loadData();//一进来请求数据
//        refreshLayout.setRefreshing(true);//一进来就刷新方式请求数据  // TODO: 17/4/14 这个有错误
        mAdapter.setAddHaed(true);
        can_content_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        can_content_view.setAdapter(mAdapter);

    }

    /**
     * 初始化数据
     */
    private void loadData() {
        Map<String, String> map = ParamsUtils.getParams();
        map.put("type", "1");
        map.put("page", page + "");
        OkHttpUtils.post().url(Api.HOME_URL).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                LiveEntity live = gson.fromJson(response, LiveEntity.class);
                delData(live);
            }
        });

    }


    private void delData(LiveEntity live) {
        if (live.getError_code() == 0) {
            if (page == 1) {//第一页
                if (live.getResult().getList() != null && live.getResult().getList().size() > 0) {//不为空
                    mAdapter.setInitData(live.getResult().getList());
                    srl_layout.setRefreshing(false);
                    mAdapter.setLoadingView(R.layout.load_loading);
                } else {
                    srl_layout.setRefreshing(false);
                    mAdapter.setLoadingView(R.layout.load_end);
                }

            } else {
                if (live.getResult().getList() != null && live.getResult().getList().size() > 0) {//不为空
                    mAdapter.setLoadMoreData(live.getResult().getList());
                } else {
                    mAdapter.setLoadingView(R.layout.load_end);
                }

            }


        } else {
            Toast.makeText(getActivity(), "白百合出轨了", Toast.LENGTH_SHORT).show();
        }


    }

}

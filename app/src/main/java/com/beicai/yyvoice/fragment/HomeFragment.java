package com.beicai.yyvoice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beicai.yyvoice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/4/12.
 */

public class HomeFragment extends Fragment {

    private ViewPager vp;
    private TabLayout tabLayout;
    private View view;
    private ArrayList list_title;
    private ArrayList list_fragment;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        mContext =context;
                super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("关注"), false);
        tabLayout.addTab(tabLayout.newTab().setText("热门"), true);//添加 Tab,默认选中
        tabLayout.addTab(tabLayout.newTab().setText("附近"), false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("视频"), false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("游戏"), false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("才艺"), false);
        tabLayout.addTab(tabLayout.newTab().setText("好声音"), false);
        vp = (ViewPager) view.findViewById(R.id.viewpager);

        AttentionFragment attentionFragment = new AttentionFragment();
        AttentionFragment attentionFragment1 = new AttentionFragment();
        AttentionFragment attentionFragment2 = new AttentionFragment();
        AttentionFragment attentionFragment3 = new AttentionFragment();
        AttentionFragment attentionFragment4 = new AttentionFragment();
        AttentionFragment attentionFragment5 = new AttentionFragment();
        AttentionFragment attentionFragment6 = new AttentionFragment();

        list_title = new ArrayList<>();
        list_title.add("关注");
        list_title.add("热门");
        list_title.add("附近");
        list_title.add("视频");
        list_title.add("游戏");
        list_title.add("才艺");
        list_title.add("好声音");
        list_fragment = new ArrayList<>();
        list_fragment.add(attentionFragment);
        list_fragment.add(attentionFragment1);
        list_fragment.add(attentionFragment2);
        list_fragment.add(attentionFragment3);
        list_fragment.add(attentionFragment4);
        list_fragment.add(attentionFragment5);
        list_fragment.add(attentionFragment6);

        NewsAdapter adapter = new NewsAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
        return view;
    }

    public class NewsAdapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;                         //fragment列表
        private List<String> list_Title;                              //tab名的列表

        public NewsAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
            super(fm);
            this.list_fragment = list_fragment;
            this.list_Title = list_Title;
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_Title.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position % list_Title.size());
        }
    }
}

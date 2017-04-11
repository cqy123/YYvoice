package com.beicai.yyvoice;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.beicai.yyvoice.fragment.AttentionFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Context mContext;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
    }

    private void initView() {

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("关注"), false);
        tabLayout.addTab(tabLayout.newTab().setText("热门"), true);//添加 Tab,默认选中
        tabLayout.addTab(tabLayout.newTab().setText("附近"), false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("视频"), false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("游戏"), false);//添加 Tab,默认不选中
        tabLayout.addTab(tabLayout.newTab().setText("才艺"), false);
        tabLayout.addTab(tabLayout.newTab().setText("好声音"), false);
        vp = (ViewPager) findViewById(R.id.viewpager);

        AttentionFragment attentionFragment = new AttentionFragment();
        AttentionFragment attentionFragment1 = new AttentionFragment();
        AttentionFragment attentionFragment2 = new AttentionFragment();
        AttentionFragment attentionFragment3 = new AttentionFragment();
        AttentionFragment attentionFragment4 = new AttentionFragment();
        AttentionFragment attentionFragment5 = new AttentionFragment();
        AttentionFragment attentionFragment6 = new AttentionFragment();

        ArrayList  list_title = new ArrayList<>();
        list_title.add("关注");
        list_title.add("热门");
        list_title.add("附近");
        list_title.add("视频");
        list_title.add("游戏");
        list_title.add("才艺");
        list_title.add("好声音");
        ArrayList list_fragment = new ArrayList<>();
        list_fragment.add(attentionFragment);
        list_fragment.add(attentionFragment1);
        list_fragment.add(attentionFragment2);
        list_fragment.add(attentionFragment3);
        list_fragment.add(attentionFragment4);
        list_fragment.add(attentionFragment5);
        list_fragment.add(attentionFragment6);

        NewsAdapter adapter = new NewsAdapter(this.getSupportFragmentManager(),list_fragment,list_title);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
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
   
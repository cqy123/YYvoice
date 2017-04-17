package com.beicai.yyvoice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.beicai.yyvoice.fragment.HomeFragment;
import com.beicai.yyvoice.fragment.MeFragment;

/**
 * Created by Yang on 2017/4/13.
 */

public class MainActivity  extends AppCompatActivity{

    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private MeFragment meFragment;
    private RadioButton rb_me;
    private RadioButton rb_home;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb_me = (RadioButton) findViewById(R.id.rb_me);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        homeFragment = new HomeFragment();
        meFragment = new MeFragment();
        ft.add(R.id.framelayout, homeFragment);
        ft.add(R.id.framelayout, meFragment);
        ft.show(homeFragment);
        ft.hide(meFragment);
        ft.commit();
        rb_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.show(homeFragment);
                ft.hide(meFragment);
                ft.commit();
            }
        });
        rb_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.show(meFragment);
                ft.hide(homeFragment);
                ft.commit();

            }
        });
        
    }
}

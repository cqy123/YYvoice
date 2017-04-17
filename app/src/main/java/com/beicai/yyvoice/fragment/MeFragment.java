package com.beicai.yyvoice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beicai.yyvoice.R;

/**
 * Created by Yang on 2017/4/12.
 */

public class MeFragment extends Fragment {
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        mContext=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_me, container, false);
        return view;//super.onCreateView(inflater, container, savedInstanceState);

    }
}

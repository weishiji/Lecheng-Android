package com.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lxg.lecheng.R;

/**
 * Created by lxg on 16/08/2017.
 */

public class FirstPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return LayoutInflater.from(getContext()).inflate(R.layout.first_fragment_layout,null);
        return inflater.inflate(R.layout.first_fragment_layout, container, false);
    }
}

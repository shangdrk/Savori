package com.zoray.savori.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoray.savori.R;

public class FragmentAccount extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_default, container, false);

        return rootView;
    }

    public static FragmentAccount getInstance() {
        return new FragmentAccount();
    }
}

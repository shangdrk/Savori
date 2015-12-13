package com.zoray.savori.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoray.savori.R;
import com.zoray.savori.adapters.HistoryRecyclerAdapter;

public class FragmentHistory extends Fragment {

    public final static String TAG = "FRAGMENT_HISTORY";

    private FragmentTabHost tabHost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_history);
        tabHost.addTab(tabHost.newTabSpec(SubFragmentUpcoming.TAG)
                .setIndicator(SubFragmentUpcoming.INDICATOR), SubFragmentUpcoming.class, null);
        tabHost.addTab(tabHost.newTabSpec(SubFragmentPrevious.TAG)
                .setIndicator(SubFragmentPrevious.INDICATOR), SubFragmentPrevious.class, null);

        return tabHost;
    }

    public static FragmentHistory getInstance() {
        return new FragmentHistory();
    }

    public HistoryRecyclerAdapter getUpcomingAdapter() {
        Fragment childFragment = getChildFragmentManager()
                .findFragmentByTag(SubFragmentUpcoming.TAG);
        return ((SubFragmentUpcoming) childFragment).getAdapter();
    }
}

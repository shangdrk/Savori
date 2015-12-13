package com.zoray.savori.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoray.savori.MainActivity;
import com.zoray.savori.R;
import com.zoray.savori.adapters.HistoryRecyclerAdapter;

public class SubFragmentPrevious extends Fragment {

    public static final String TAG = "SUB_FRAGMENT_PREVIOUS";
    public static final String INDICATOR = "Previous";

    private HistoryRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_sub_previous, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.previous_recycler_view);
        adapter = new HistoryRecyclerAdapter(getActivity());
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        if (((MainActivity)getContext()).getTransactionList() != null &&
                ((MainActivity)getContext()).getTransactionList().size() != 0) {
            adapter.update(((MainActivity)getContext()).getTransactionList());
        }

        return rootView;
    }

    public HistoryRecyclerAdapter getAdapter() {
        return adapter;
    }
}

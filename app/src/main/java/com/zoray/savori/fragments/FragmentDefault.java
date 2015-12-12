package com.zoray.savori.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zoray.savori.R;
import com.zoray.savori.adapters.SearchRecyclerViewAdapter;
//import com.zoray.savori.SearchActivity;

public class FragmentDefault extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_default, container, false);

        final SearchView searchView = (SearchView) rootView.findViewById(R.id.default_search);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        //searchView.requestFocusFromTouch();
<<<<<<< HEAD
=======

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(getContext());

        recyclerView.setHasFixedSize(false);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(
                getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
>>>>>>> b33d8f59630e5ed35e81c003a6055ec8f6586775

        return rootView;
    }


    public static FragmentDefault getInstance() {
        return new FragmentDefault();
    }
}

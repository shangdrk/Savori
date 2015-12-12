package com.zoray.savori.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoray.savori.R;
import com.zoray.savori.adapters.SearchRecyclerViewAdapter;
import com.zoray.savori.data.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amandayin on 12/12/15.
 */
public class FragmentSearchResult extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d("mylog", "enter fmSearchResult?");

        // result of the search TODO
<<<<<<< HEAD

        ArrayList<String> resultIDs = getArguments().getStringArrayList("resultIDs");
        SearchResult searchResult = new SearchResult(resultIDs.get(0),false);
        SearchResult searchResult2 = new SearchResult(resultIDs.get(1),false);
        List<SearchResult> list = new ArrayList<SearchResult>();
        list.add(searchResult);
        list.add(searchResult2);


        View rootView = inflater.inflate(
                R.layout.fragment_result_general, container, false);

        Log.d("mylog", "enter fmSearchResult3");


        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
=======
        String searchResultString = getArguments().getString("SEARCH_RESULT");
        SearchResult searchResult = new SearchResult(searchResultString,false);
        List<SearchResult> list = new ArrayList<SearchResult>();
        list.add(searchResult);
        list.add(searchResult);

        View rootView = inflater.inflate(
                R.layout.fragment_default, container, false);

        final SearchView searchView = (SearchView) rootView.findViewById(R.id.default_search);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        //searchView.requestFocusFromTouch();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        //TODO

>>>>>>> b33d8f59630e5ed35e81c003a6055ec8f6586775
        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(getContext(),list);

        recyclerView.setHasFixedSize(false);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(
                getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        Log.d("mylog", "exit fmSearchResult?");

        return rootView;

    }



}

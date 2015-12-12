package com.zoray.savori.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

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

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.zoray.savori.R;
import com.zoray.savori.ResultActivity;
import com.zoray.savori.adapters.SearchRecyclerViewAdapter;
import com.zoray.savori.data.Dish;
import com.zoray.savori.data.SearchResult;

import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amandayin on 12/12/15.
 */
public class FragmentSearchResult extends Fragment {

    private List<SearchResult> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(
                R.layout.fragment_result_general, container, false);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(getContext());

        recyclerView.setHasFixedSize(false);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(
                getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        ArrayList<String> resultIDs = ((ResultActivity)getActivity()).getSearchResultIds();
        list = new ArrayList<SearchResult>();

        for (int i = 0; i <= resultIDs.size() - 1; ++i) {

            ParseUser user = ParseUser.getCurrentUser();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
            query.getInBackground(resultIDs.get(i), new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        String dishName = object.getString("dishName");
                        SearchResult searchResult = new SearchResult(dishName, false);
                        searchResult.setParseId(object.getObjectId());
                        list.add(searchResult);

                    } else {
                        // something went wrong
                    }

                    ((SearchRecyclerViewAdapter)recyclerView.getAdapter()).updateResultList(list);
                }
            });

        }

        return rootView;

    }


}

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
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseFile;
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

    private boolean errorTag;
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

        final TextView tvSearchError = (TextView) rootView.findViewById(R.id.tvSearchError);
        if (errorTag){
            tvSearchError.setVisibility(View.VISIBLE);

        }else{
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
                            String price = object.getString("price");
                            ParseFile dishImage = object.getParseFile("picture");
                            byte[] imageBytes = new byte[0];
                            try {
                                imageBytes = dishImage.getData();
                            }catch (ParseException ex){
                                ex.printStackTrace();
                            }
                            SearchResult searchResult = new SearchResult();
                            searchResult.setName(dishName);
                            searchResult.setPrice(price);
                            searchResult.setDishImage(imageBytes);
                            searchResult.setParseId(object.getObjectId());

                            list.add(searchResult);

                        } else {

                        }

                        if (list.isEmpty()){
                            tvSearchError.setVisibility(View.VISIBLE);
                        }
                        else {
                            ((SearchRecyclerViewAdapter) recyclerView.getAdapter()).updateResultList(list);
                        }
                    }
                });
            }
        }
        return rootView;
    }

    public void setErrorTag(boolean err){
        if (err){
            errorTag = err;
        }
    }
}

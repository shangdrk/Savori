package com.zoray.savori;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.zoray.savori.data.SearchResult;
import com.zoray.savori.fragments.FragmentResultDetails;
import com.zoray.savori.fragments.FragmentSearchResult;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private ArrayList<String> searchResultIDs = new ArrayList<>();
    private String resultId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Log.d("mylog", "oncreate fragment");

        /*ActionBar acbar = getSupportActionBar();
        Log.d("mylog", "1");
        acbar.setDisplayHomeAsUpEnabled(true);
        Log.d("mylog", "2");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Log.d("mylog", "get support action bar success");*/

        Intent intent = getIntent();
        String keyword = intent.getExtras().getString("keyword").toLowerCase();

        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
        query.whereContains("dishName", keyword);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentSearchResult fragment = new FragmentSearchResult();

                if (e == null) {
                    for (int i = 0; i <= objects.size() - 1; ++i) {
                        searchResultIDs.add(objects.get(i).getObjectId());
                    }
                } else {
                    fragment.setErrorTag(true);
                }

                fragmentTransaction.add(R.id.FragmentContainer, fragment);
                fragmentTransaction.commit();
            }

        });
}

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }*/

    public void showDetail(String resultId) {

        this.resultId = resultId;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentResultDetails fragment = new FragmentResultDetails();

        fragmentTransaction.replace(R.id.FragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public ArrayList<String> getSearchResultIds(){

        return searchResultIDs;
    }

    public String getResultId(){

        return resultId;
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}

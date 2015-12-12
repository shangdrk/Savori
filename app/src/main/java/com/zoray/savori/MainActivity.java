package com.zoray.savori;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.zoray.savori.adapters.MainFragmentPagerAdapter;
import com.zoray.savori.data.HistoryRow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    private List<HistoryRow> historyRowList;
    private List<HistoryRow> upcomingRowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set ViewPager adapter
        pagerAdapter = new MainFragmentPagerAdapter(
                getSupportFragmentManager(), getApplicationContext()
        );
        viewPager = (ViewPager) findViewById(R.id.main_container);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // set history
        if (ParseUser.getCurrentUser() != null) {
            inflateHistoryRecyclerView();
            inflateAccountView();
        }

        // set SearchView
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Log.d("mylog", "start search?");
            String query = intent.getStringExtra(SearchManager.QUERY);
            showResults(query);
        }else {
        }
        Log.d("mylog", "exit on create but why?");
    }

    private void showResults(String query) {

        Intent intent  = new Intent(getApplicationContext(),ResultActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

    private void inflateHistoryRecyclerView() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("History");

        query.whereEqualTo("userEmail", ParseUser.getCurrentUser().getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.get(0).getList("transactionArray").size() != 0) {
                        historyRowList = new ArrayList<>();
                        upcomingRowList = new ArrayList<>();
                    }
                    for (Object row : objects.get(0).getList("transactionArray")) {
                        if (((HistoryRow) row).getIsFinished()) {
                            historyRowList.add((HistoryRow)row);
                        } else {
                            upcomingRowList.add((HistoryRow) row);
                        }
                    }
                } else {
                    // TODO: HANDLE EXCEPTION
                }
            }
        });
    }

    private void inflateAccountView() {
        // TODO: IMPLEMENTATION
    }

    public List<HistoryRow> getHistoryRowList() {
        return historyRowList;
    }
}

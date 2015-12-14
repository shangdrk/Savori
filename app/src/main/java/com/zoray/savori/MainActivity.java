package com.zoray.savori;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.zoray.savori.adapters.MainFragmentPagerAdapter;
import com.zoray.savori.data.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    private List<Transaction> transactionList;
    private List<Transaction> upcomingRowList;

    private byte[] profilePic;
    private String userName;

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
            String keyword = intent.getStringExtra(SearchManager.QUERY);
            showResults(keyword);
        }else
        {


        }
    }

    private void showResults(String keyword) {
        Intent intent  = new Intent(getApplicationContext(),ResultActivity.class);
        intent.putExtra("keyword", keyword);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void inflateHistoryRecyclerView() {

        ParseQuery<Transaction> query = ParseQuery.getQuery(Transaction.class);
        query.whereEqualTo("buyer", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Transaction>() {
            @Override
            public void done(List<Transaction> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() == 0) return;

                    transactionList = new ArrayList<>();
                    upcomingRowList = new ArrayList<>();
                    for (Transaction trans : objects) {
                        if (trans.getIsFinished()) {
                            transactionList.add(trans);
                        } else {
                            upcomingRowList.add(trans);
                        }
                    }
                }
            }
        });
    }

    private void inflateAccountView() {
        try{
            profilePic = ParseUser.getCurrentUser().fetchIfNeeded().getParseFile("picture").getData();
            userName = ParseUser.getCurrentUser().get("firstName") + " " + ParseUser.getCurrentUser().get("lastName");
        }catch (ParseException ex){
            ex.printStackTrace();
        }
    }

    public void changeTab(int positon) {
        viewPager.setCurrentItem(positon, true);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public List<Transaction> getUpcomingRowList() {
        return upcomingRowList;
    }

    public byte[] getProfilePic(){
        return profilePic;
    }

    public String getUserName() {
        return userName;
    }
}

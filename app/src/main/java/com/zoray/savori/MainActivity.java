package com.zoray.savori;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zoray.savori.adapters.MainFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private MainFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerAdapter = new MainFragmentPagerAdapter(
                getSupportFragmentManager(), getApplicationContext()
        );
        viewPager = (ViewPager) findViewById(R.id.main_container);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

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

}

package com.zoray.savori;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}

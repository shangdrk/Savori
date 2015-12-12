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

import com.parse.ParseObject;
import com.zoray.savori.data.SearchResult;
import com.zoray.savori.fragments.FragmentResultDetails;
import com.zoray.savori.fragments.FragmentSearchResult;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

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
        String query = intent.getExtras().getString("query");

        // suppose query database is done

        // display general results in the fragment
        ArrayList<String> searchResultIDs = new ArrayList<>();
        searchResultIDs.add("1234");
        searchResultIDs.add("5678");

        Log.d("mylog", "receive query successfully");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentSearchResult fragment = new FragmentSearchResult();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("resultIDs", searchResultIDs);
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.FragmentContainer, fragment);
        fragmentTransaction.commit();

        Log.d("mylog", "fm manager and transaction?");

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

    public void showDetail(String resultId){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentResultDetails fragment = new FragmentResultDetails();

        Bundle bundle = new Bundle();
        bundle.putString("resultID", resultId);




        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.FragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

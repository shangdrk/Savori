package com.zoray.savori.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.zoray.savori.MainActivity;
import com.zoray.savori.R;
import com.zoray.savori.ResultActivity;
import com.zoray.savori.adapters.SearchRecyclerViewAdapter;
import com.zoray.savori.data.Dish;
import com.zoray.savori.data.SearchResult;

public class FragmentResultDetails extends Fragment {

    public final static String TAG = "FRAGMENT_RESULT_DETAILS";
    private String resultId;
    private LinearLayout layoutContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_result_details, container, false);

        resultId = ((ResultActivity)getActivity()).getResultId();

        layoutContent = (LinearLayout) rootView.findViewById(R.id.layoutContent_Detail);

        final TextView textViewid = (TextView) rootView.findViewById(R.id.dishId);

        final TextView tvDishName = (TextView) rootView.findViewById(R.id.dishName);

        final Button btnOrder = (Button) rootView.findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: actually place the order!


                showSnackBarMessage("Your order has been placed!");

            }
        });

        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
        query.getInBackground(resultId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                Dish dish = new Dish();

                if (e == null) {
                    String dishName = object.getString("dishName");
                    String dishPrice = object.getString("price");
                    String parseId = object.getObjectId();

                    dish.setDishName(dishName);
                    dish.setPrice(dishPrice);
                    dish.setParseID(parseId);


                } else {
                    // something went wrong
                }

                textViewid.setText( dish.getParseID() );
                tvDishName.setText( dish.getDishName() );

            }
        });


        return rootView;
    }

    private void showSnackBarMessage(String message) {
        Snackbar.make(layoutContent,
                message,
                Snackbar.LENGTH_LONG
        ).setAction("Got it", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }).show();
    }
}

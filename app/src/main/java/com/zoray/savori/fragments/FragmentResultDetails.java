package com.zoray.savori.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zoray.savori.R;
import com.zoray.savori.ResultActivity;

public class FragmentResultDetails extends Fragment {

    public final static String TAG = "FRAGMENT_RESULT_DETAILS";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_result_details, container, false);

        TextView textViewid = (TextView) rootView.findViewById(R.id.dishId);

        textViewid.setText( (  (ResultActivity)getActivity() ).getResultId() );

        TextView tvDishName = (TextView) rootView.findViewById(R.id.dishName);


        return rootView;
    }
}

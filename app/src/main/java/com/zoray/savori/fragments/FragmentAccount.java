package com.zoray.savori.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.zoray.savori.LoginActivity;
import com.zoray.savori.MainActivity;
import com.zoray.savori.R;

public class FragmentAccount extends Fragment {

    public final static String TAG = "FRAGMENT_ACCOUNT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_account, container, false);
        TextView tvAccountLogout = (TextView) rootView.findViewById(R.id.tvAccountLogout);
        tvAccountLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        performLogOut();
                    }
                });
            }
        });

        ImageView ivAccountPicture = (ImageView) rootView.findViewById(R.id.ivAccountPicture);
        byte[] profilePic = new byte[0];

        TextView tvUserName = (TextView) rootView.findViewById(R.id.tvAccountName);

        if (ParseUser.getCurrentUser()!= null){
            profilePic = ((MainActivity)getContext()).getProfilePic();
            Log.d("mylog", "profile picture length " + String.valueOf(profilePic.length));
            Bitmap profileImageBmp = BitmapFactory.decodeByteArray(profilePic, 0, profilePic.length);
            ivAccountPicture.setImageBitmap(profileImageBmp);

            tvUserName.setText(((MainActivity)getContext()).getUserName());
        }

        TextView tvAccountHistory = (TextView) rootView.findViewById(R.id.tvAccountHistory);
        tvAccountHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeTab(1);
            }
        });

        return rootView;
    }

    public static FragmentAccount getInstance() {
        return new FragmentAccount();
    }

    public void performLogOut() {
        Intent intentLogin = new Intent();
        intentLogin.setClass(getContext(), LoginActivity.class);
        intentLogin.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intentLogin);
    }
}


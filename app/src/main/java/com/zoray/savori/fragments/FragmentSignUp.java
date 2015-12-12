package com.zoray.savori.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.zoray.savori.LoginActivity;
import com.zoray.savori.R;

public class FragmentSignUp extends Fragment {

    public final static String TAG = "FRAGMENT_SIGN_UP";

    ImageView ivBack;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    TextView submit;

    public FragmentSignUp() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ivBack = (ImageView) rootView.findViewById(R.id.ivBack_arrow);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        firstName = (EditText) rootView.findViewById(R.id.signUp_firstName);
        lastName = (EditText) rootView.findViewById(R.id.signUp_lastName);
        email = (EditText) rootView.findViewById(R.id.signUp_email);
        password = (EditText) rootView.findViewById(R.id.signUp_password);
        submit = (TextView) rootView.findViewById(R.id.signUp_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ensureValidInput()) {
                    signUpUser();
                }
            }
        });


        return rootView;
    }

    public boolean ensureValidInput() {
        if (firstName.getText().toString().equals("")) {
            firstName.setError("This field cannot be empty");
            return false;
        } else if (lastName.getText().toString().equals("")) {
            lastName.setError("This field cannot be empty");
            return false;
        } else if (email.getText().toString().equals("")) {
            email.setError("This field cannot be empty");
            return false;
        } else if (password.getText().toString().equals("")) {
            password.setError("This field cannot be empty");
            return false;
        }

        if (!((LoginActivity) getActivity()).isCorrectEmailFormat(email.getText().toString())) {
            email.setError(getString(R.string.wrong_email_error));
            return false;
        }
        if (!((LoginActivity) getActivity()).isValidPassword(password.getText().toString())) {
            password.setError(getString(R.string.invalid_password_error));
            return false;
        }

        return true;
    }

    public void signUpUser() {
        ParseUser user = new ParseUser();
        user.setEmail(email.getText().toString());
        user.setUsername(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.put("firstName", firstName.getText().toString());
        user.put("lastName", lastName.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    getActivity().onBackPressed();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}

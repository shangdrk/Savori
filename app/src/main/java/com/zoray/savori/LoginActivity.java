package com.zoray.savori;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.zoray.savori.fragments.FragmentSignUp;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        if (ParseUser.getCurrentUser() != null) {
            showMainActivity();
        }

        setContentView(R.layout.activity_login);

        final EditText etEmail = (EditText) findViewById(R.id.login_email);
        final EditText etPassword = (EditText) findViewById(R.id.login_password);

        TextView tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCorrectEmailFormat(etEmail.getText().toString())) {
                    etEmail.setError(getString(R.string.wrong_email_error));
                }
                if (!isValidPassword(etPassword.getText().toString())) {
                    etPassword.setError(getString(R.string.invalid_password_error));
                }

                attemptLogin(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        TextView tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSignUpFragment();
            }
        });

        LoginButton fbLogin = (LoginButton) findViewById(R.id.fb_login_button);
        fbLogin.setReadPermissions("public_profile", "email");
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // TODO: implementation
                accessToken = loginResult.getAccessToken();
            }

            @Override
            public void onCancel() {
                // TODO: implementation
            }

            @Override
            public void onError(FacebookException error) {
                // TODO: implementation
            }
        });
    }

    private void ShowSignUpFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment signUp = new FragmentSignUp();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );
        transaction.replace(R.id.layout_login, signUp, FragmentSignUp.TAG);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isCorrectEmailFormat(String email) {
        return email.contains("@") && email.contains(".");
    }

    public boolean isValidPassword(String password) {
        return (password != null) && (password.length() >= 7);
    }

    private void showMainActivity() {
        Intent intentMain = new Intent();
        intentMain.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intentMain);
    }

    public void attemptLogin(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    showMainActivity();
                } else {
                    // TODO: HANDLE
                }
            }
        });
    }

}

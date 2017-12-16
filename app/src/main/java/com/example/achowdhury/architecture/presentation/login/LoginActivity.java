package com.example.achowdhury.architecture.presentation.login;

import android.widget.Toast;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity implements LoginMVP.View {

    @Override
    public void showLoginFailed(LoginFailError error) {
        if(error == LoginFailError.INTERNETCONNECTIVITY) {
            Toast.makeText(getApplicationContext(), "Internet Connectivity Error", Toast.LENGTH_LONG).show();
        }
        else if(error == LoginFailError.INVALIDSIGNIN) {
            Toast.makeText(getApplicationContext(), "Invalid Signon", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Login Fail Error Occured", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showLoginSucceeded() {

    }
}

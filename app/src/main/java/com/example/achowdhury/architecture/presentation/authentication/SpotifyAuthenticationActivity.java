package com.example.achowdhury.architecture.presentation.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.achowdhury.architecture.R;
import com.example.achowdhury.architecture.presentation.login.LoginActivity;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.authentication.BuildConfig;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SpotifyAuthenticationActivity extends DaggerAppCompatActivity implements SpotifyAuthenticationMVP.View {
    private static final int REQUEST_CODE = 123;
    private static final String CLIENT_ID = "9e06fe072a924b31962795d0a504056d";
    private static final String REDIRECTURI = "architecture://callback";
    public static final String SPOTIFY_TOKEN_EXTRA = "architecture.presentation.login";
    public static String spotifyAccessToken = "";

    @Inject
    SpotifyAuthenticationMVP.Presenter presenter;

    @Inject
    Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);

        presenter.takeView(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
        presenter.handleLoginResponse(response);
    }

    @Override
    public void promptAuthentication() {
        AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECTURI).build();
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    public void showAuthFailed(String error) {
        if(BuildConfig.DEBUG) {
            Toast.makeText(appContext, error, Toast.LENGTH_LONG).show();
        }

        if(error.equals("Spotify Account Needed")) {
            Toast.makeText(appContext, error, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void showAuthSucceeded() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(SPOTIFY_TOKEN_EXTRA, spotifyAccessToken);

    }

    @Override
    public void onStop() {
        super.onStop();

        presenter.dropView();
    }
}
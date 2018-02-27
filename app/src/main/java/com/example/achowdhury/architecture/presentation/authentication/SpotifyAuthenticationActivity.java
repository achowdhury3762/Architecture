package com.example.achowdhury.architecture.presentation.authentication;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.achowdhury.architecture.R;
import com.example.achowdhury.architecture.presentation.login.LoginActivity;
import com.example.achowdhury.architecture.util.AnimationUtils;
import com.example.achowdhury.architecture.util.picasso.ImageBackgroundRelativeLayout;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.authentication.BuildConfig;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SpotifyAuthenticationActivity extends DaggerAppCompatActivity implements SpotifyAuthenticationMVP.View {
    private static final int REQUEST_CODE = 123;
    private static final String CLIENT_ID = "9e06fe072a924b31962795d0a504056d";
    private static final String REDIRECTURI = "architecture://callback";
    public static final String SPOTIFY_TOKEN_EXTRA = "architecture.presentation.login";
    public static String spotifyAccessToken = "";
    private static final int ANIMATION_TIME_MS = 2500;

    @Inject
    SpotifyAuthenticationMVP.Presenter presenter;

    @Inject
    Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        presenter.takeView(this);

        setBackground();
    }

    private void setBackground() {
        ImageBackgroundRelativeLayout layout = (ImageBackgroundRelativeLayout) findViewById(R.id.background_layout);
        Picasso.with(appContext).load(R.drawable.aux_background).resize(50,50).into(layout);
    }

    private void animateImage() {
        ImageView logoBrightImageView = (ImageView) findViewById(R.id.logo_bright_image_view);
        ImageView logoDullImageView = (ImageView) findViewById(R.id.logo_dull_image_view);

        AnimationUtils.animateImageGlow(logoDullImageView, logoBrightImageView, ANIMATION_TIME_MS)
                .addListener(new AnimationUtils.EndAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        continueToLoginActivity();

                        finish();
                    }
                });
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
        animateImage();
    }

    public void continueToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(SPOTIFY_TOKEN_EXTRA, spotifyAccessToken);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_out_left);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
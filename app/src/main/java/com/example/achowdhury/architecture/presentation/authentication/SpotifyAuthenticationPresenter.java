package com.example.achowdhury.architecture.presentation.authentication;

import android.support.annotation.Nullable;

import com.spotify.sdk.android.authentication.*;

import javax.inject.Inject;

final class SpotifyAuthenticationPresenter implements SpotifyAuthenticationMVP.Presenter<SpotifyAuthenticationMVP.View> {

    @Nullable
    private SpotifyAuthenticationMVP.View spotifyAuthView;

    @Inject
    SpotifyAuthenticationPresenter(){}

    @Override
    public void takeView(SpotifyAuthenticationMVP.View v) {
        this.spotifyAuthView = v;

        promptAuthentication();
    }

    @Override
    public void handleLoginResponse(AuthenticationResponse response) {
        if(response == null) {
            throw new IllegalArgumentException("Response should never be null");
        }
        else if(response.getType() == AuthenticationResponse.Type.ERROR) {
            if(response.getError().equalsIgnoreCase("access_denied")) {
                spotifyAuthView.showAuthFailed("Spotify Account Needed");
            }
        }
        else if(response.getType() == AuthenticationResponse.Type.TOKEN) {
            SpotifyAuthenticationActivity.spotifyAccessToken = response.getAccessToken();

            spotifyAuthView.showAuthSucceeded();
        }
        else {
            spotifyAuthView.showAuthFailed("Login Error, Try Again");
            spotifyAuthView.promptAuthentication();
        }
    }


    private void promptAuthentication() {
        if(spotifyAuthView == null) {
            return;
        }

        spotifyAuthView.promptAuthentication();
    }

    @Override
    public void dropView() {
        spotifyAuthView = null;
    }
}
package com.example.achowdhury.architecture.presentation.authentication;

import android.support.annotation.Nullable;

import com.example.achowdhury.architecture.data.SpotifyUtil;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import javax.inject.Inject;

final class SpotifyAuthenticationPresenter implements SpotifyAuthenticationMVP.Presenter<SpotifyAuthenticationMVP.View> {

    @Nullable
    private SpotifyAuthenticationMVP.View spotifyAuthView;

    private SpotifyUtil spotifyUtil;

    @Inject
    SpotifyAuthenticationPresenter(){
        spotifyUtil = SpotifyUtil.getInstance();
    }

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

            if(response.getAccessToken().equals("")) {
                spotifyAuthView.showAuthFailed("Empty Token");
            }

            else
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
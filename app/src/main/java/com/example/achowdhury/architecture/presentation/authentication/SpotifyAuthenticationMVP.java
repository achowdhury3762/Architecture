package com.example.achowdhury.architecture.presentation.authentication;

import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class SpotifyAuthenticationMVP {
    interface View {
        void promptAuthentication();

        void showAuthFailed(String loginError);

        void showAuthSucceeded();
    }

    interface Presenter<View> {
        void handleLoginResponse(AuthenticationResponse response);

        void takeView(View v);

        void dropView();
    }
}

package com.example.achowdhury.architecture.presentation.login;

class LoginMVP {
    interface View {
        void showLoginFailed(LoginFailError error);

        void showLoginSucceeded();
    }

    interface Presenter<View> {
        void takeView(View v);

        void dropView();

        void checkLogin(String username, String password);
    }
}

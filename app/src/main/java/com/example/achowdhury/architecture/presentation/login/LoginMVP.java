package com.example.achowdhury.architecture.presentation.login;

class LoginMVP {
    interface View {
        void showLoginFailed(LoginFailError error);

        void showLoginSucceeded();

        void showKeyboardAnimationUp();

        void showKeyboardAnimationDown();

        void showLoad();

        void hideLoad();
    }

    interface Presenter<View> {
        void takeView(View v);

        void onEditTextFocus(boolean focus);

        void checkLogin(String username, String password);

        void onResume();

        void dropView();
    }
}

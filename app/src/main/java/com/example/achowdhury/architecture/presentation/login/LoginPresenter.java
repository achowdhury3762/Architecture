package com.example.achowdhury.architecture.presentation.login;

import javax.inject.Inject;

class LoginPresenter implements LoginMVP.Presenter<LoginMVP.View> {

    private LoginMVP.View loginView;
    private boolean upAnimationState;

    @Inject
    LoginPresenter() {}

    @Override
    public void takeView(LoginMVP.View v) {
        loginView = v;

        upAnimationState = false;
    }

    @Override
    public void onEditTextFocus(boolean focus) {
        if(focus && !upAnimationState) {
            loginView.showKeyboardAnimationUp();
            upAnimationState = true;
        }
    }

    @Override
    public void dropView() {
        loginView = null;
    }

    @Override
    public void checkLogin(String username, String password) {
        if(password.length() < 3) {

        }
        boolean validPassword = checkPasswordValidity(password);
    }

    @Override
    public void onResume() {
        if(upAnimationState) {
            loginView.showKeyboardAnimationDown();
            upAnimationState = false;
        }
    }

    private boolean checkPasswordValidity(String password) {
        return false;
    }
}
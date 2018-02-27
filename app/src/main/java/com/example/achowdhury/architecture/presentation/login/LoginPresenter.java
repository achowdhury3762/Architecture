package com.example.achowdhury.architecture.presentation.login;

import javax.inject.Inject;

class LoginPresenter implements LoginMVP.Presenter<LoginMVP.View>, LoginService.LoginResponseCallback {

    private LoginMVP.View loginView;
    private boolean upAnimationState;

    @Inject
    LoginService loginService;

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
        if(loginView == null) {
            return;
        }

        if(username.length() < 3) {
            loginView.showLoginFailed(LoginFailError.SHORTUSERNAME);
            return;
        } else if(password.length() < 3) {
            loginView.showLoginFailed(LoginFailError.SHORTPASSWORD);
            return;
        }

        loginView.showLoad();

        loginService.login(username, password, this);
    }

    @Override
    public void onResume() {
        if(upAnimationState) {
            loginView.showKeyboardAnimationDown();
            upAnimationState = false;
        }
    }

    @Override
    public void onLoginSuccess() {
        loginView.hideLoad();
        loginView.showLoginSucceeded();
    }

    @Override
    public void onLoginFailure() {
        loginView.hideLoad();
        loginView.showLoginFailed(LoginFailError.INTERNETCONNECTIVITY);
    }
}
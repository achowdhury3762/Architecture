package com.example.achowdhury.architecture.presentation.login;

import javax.inject.Inject;

public class LoginPresenter implements LoginMVP.Presenter<LoginMVP.View> {

    private LoginMVP.View loginView;

    @Inject
    LoginPresenter() {}

    @Override
    public void takeView(LoginMVP.View v) {
        loginView = v;
    }

    @Override
    public void dropView() {
        loginView = null;
    }

    @Override
    public void checkLogin(String username, String password) {

    }
}

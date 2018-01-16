package com.example.achowdhury.architecture.presentation.login;

import javax.inject.Inject;

class LoginPresenter implements LoginMVP.Presenter<LoginMVP.View> {

    private LoginMVP.View loginView;
    private boolean editTextFocus;

    @Inject
    LoginPresenter() {}

    @Override
    public void takeView(LoginMVP.View v) {
        loginView = v;

        editTextFocus = true;
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
    public void clickTextBox() {
        if(editTextFocus) {
            loginView.pushTextBoxUp();
        }
    }

    private boolean checkPasswordValidity(String password) {
        return false;
    }
}

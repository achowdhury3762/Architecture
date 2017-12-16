package com.example.achowdhury.architecture.presentation.login;

import com.example.achowdhury.architecture.di.PerActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginModule {

//    @PerFragment
//    @ContributesAndroidInjector
//    abstract LoginFragment loginFragment();

    @PerActivity
    @Binds
    abstract LoginMVP.Presenter loginPresenter(LoginPresenter presenter);
}

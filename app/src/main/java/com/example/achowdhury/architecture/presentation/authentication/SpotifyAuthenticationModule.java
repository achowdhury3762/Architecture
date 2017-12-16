package com.example.achowdhury.architecture.presentation.authentication;

import com.example.achowdhury.architecture.di.PerActivity;
import com.example.achowdhury.architecture.di.PerFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SpotifyAuthenticationModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract SpotifyAuthenticationFragment spotifyAuthFragment();

    @PerActivity
    @Binds
    abstract SpotifyAuthenticationMVP.Presenter spotifyAuthPresenter(SpotifyAuthenticationPresenter loginPresenter);
}

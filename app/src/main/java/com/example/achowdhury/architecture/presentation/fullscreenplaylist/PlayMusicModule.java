package com.example.achowdhury.architecture.presentation.fullscreenplaylist;

import com.example.achowdhury.architecture.di.PerActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PlayMusicModule {
    @PerActivity
    @Binds
    abstract PlayMusicMVP.Presenter playMusicPresenter(PlayMusicPresenter presenter);
}
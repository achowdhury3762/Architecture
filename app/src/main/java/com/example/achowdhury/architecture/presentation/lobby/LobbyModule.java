package com.example.achowdhury.architecture.presentation.lobby;

import com.example.achowdhury.architecture.di.PerActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LobbyModule {
    @PerActivity
    @Binds
    abstract LobbyMVP.Presenter lobbyPresenter(LobbyPresenter presenter);
}

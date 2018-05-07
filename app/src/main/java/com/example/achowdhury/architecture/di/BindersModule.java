package com.example.achowdhury.architecture.di;

import com.example.achowdhury.architecture.presentation.authentication.SpotifyAuthenticationActivity;
import com.example.achowdhury.architecture.presentation.authentication.SpotifyAuthenticationModule;
import com.example.achowdhury.architecture.presentation.fullscreenplaylist.PlayMusicActivity;
import com.example.achowdhury.architecture.presentation.lobby.LobbyActivity;
import com.example.achowdhury.architecture.presentation.lobby.LobbyModule;
import com.example.achowdhury.architecture.presentation.login.LoginActivity;
import com.example.achowdhury.architecture.presentation.login.LoginModule;
import com.example.achowdhury.architecture.presentation.fullscreenplaylist.PlayMusicModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class BindersModule {
    @PerActivity
    @ContributesAndroidInjector(modules = SpotifyAuthenticationModule.class)
    abstract SpotifyAuthenticationActivity spotifyAuthenticationActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract LobbyActivity lobbyActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = PlayMusicModule.class)
    abstract PlayMusicActivity playMusicActivity();
}

package com.example.achowdhury.architecture.presentation;

import com.example.achowdhury.architecture.di.AppComponent;
import com.example.achowdhury.architecture.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent app = DaggerAppComponent.builder().app(this).build();
        app.inject(this);
        return app;
    }
}

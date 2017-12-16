package com.example.achowdhury.architecture.presentation;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UIThread {
    @Inject
    UIThread() {}

    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}

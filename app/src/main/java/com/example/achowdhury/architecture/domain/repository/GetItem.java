package com.example.achowdhury.architecture.domain.repository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class GetItem<Item, Reqs> {
    private Scheduler mainThread;
    private Scheduler backgroundThread;
    private CompositeDisposable disposables;

    public GetItem(Scheduler mainThread, Scheduler backgroundThread) {
        this.mainThread = mainThread;
        this.backgroundThread = backgroundThread;

        disposables = new CompositeDisposable();
    }

    abstract Observable<Item> getUseCaseObservable(Reqs reqs);

    public void execute(DisposableObserver<Item> observer, Reqs requirements) {
        Disposable d =
                getUseCaseObservable(requirements)
                .subscribeOn(backgroundThread)
                .observeOn(mainThread)
                .subscribeWith(observer);

        disposables.add(d);
    }

    public void stopExecution() {
        disposables.dispose();
    }
}

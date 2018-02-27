package com.example.achowdhury.architecture.presentation.lobby;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LobbyPresenter implements LobbyMVP.Presenter {

    private CompositeDisposable disposables = new CompositeDisposable();

    private LobbyMVP.View view;

    @Inject
    LobbyPresenter () {}

    @Override
    public void takeView(LobbyMVP.View view) {
        this.view = view;
    }

    @Override
    public void stopServiceCalls() {
        disposables.clear();
    }

    @Override
    public void onCreateRoomClick() {
        view.showRoomCreationPopup();
    }

    @Override
    public void createRoomWithUserAndPass(String username, String password) {
//        Disposable d;
//
//        disposables.add();
    }

    @Override
    public void joinRoomWithUserAndPass(String username, String password) {

    }

    @Override
    public void onJoinRoomClick() {
//        view.showRoomJoinPopup();
    }

    @Override
    public void dropView() {
        this.view = null;

        disposables.clear();
    }

    @Override
    public void onExitPopUp() {
        view.dismissPopup();
    }
}

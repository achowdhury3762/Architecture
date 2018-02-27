package com.example.achowdhury.architecture.presentation.lobby;

public interface LobbyMVP {
    interface Presenter {
        void onCreateRoomClick();

        void onJoinRoomClick();

        void createRoomWithUserAndPass(String username, String password);

        void joinRoomWithUserAndPass(String username, String password);

        void takeView(LobbyMVP.View view);

        void stopServiceCalls();

        void dropView();

        void onExitPopUp();
    }

    interface View {
        void showRoomCreationPopup();

        void showLoad();

        void dismissPopup();

        void navigateToLoginPage();
    }
}

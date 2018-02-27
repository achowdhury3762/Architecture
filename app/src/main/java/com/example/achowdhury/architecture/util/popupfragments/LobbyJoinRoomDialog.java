package com.example.achowdhury.architecture.util.popupfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.achowdhury.architecture.R;
import com.example.achowdhury.architecture.presentation.lobby.LobbyMVP;

public class LobbyJoinRoomDialog extends DaggerDialogFragment {

    private EditText roomNameEditText;
    private EditText roomPasswordEditText;
    private CardView submitBtn;
    private CardView exitBtn;
    private Spinner spinner;
    private LobbyMVP.Presenter lobbyPresenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_join_create_room, null);
        roomNameEditText = view.findViewById(R.id.room_name);
        roomPasswordEditText = view.findViewById(R.id.room_password);

        submitBtn = view.findViewById(R.id.lobby_submit_dialog_btn);
        exitBtn = view.findViewById(R.id.lobby_cancel_dialog_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lobbyPresenter.createRoomWithUserAndPass(roomNameEditText.getText().toString(), roomPasswordEditText.getText().toString());
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lobbyPresenter.onExitPopUp();
            }
        });

        alertDialogBuilder.setView(view);
        return alertDialogBuilder.create();
    }

    public void setPresenter(LobbyMVP.Presenter lobbyPresenter) {
        this.lobbyPresenter = lobbyPresenter;
    }
}

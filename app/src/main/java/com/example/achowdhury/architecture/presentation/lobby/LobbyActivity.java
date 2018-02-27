package com.example.achowdhury.architecture.presentation.lobby;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.achowdhury.architecture.R;
import com.example.achowdhury.architecture.presentation.login.LoginActivity;
import com.example.achowdhury.architecture.util.ActivityUtil;
import com.example.achowdhury.architecture.util.picasso.ImageBackgroundLinearLayout;
import com.example.achowdhury.architecture.util.popupfragments.LobbyJoinRoomDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.example.achowdhury.architecture.presentation.authentication.SpotifyAuthenticationActivity.SPOTIFY_TOKEN_EXTRA;
import static com.example.achowdhury.architecture.presentation.authentication.SpotifyAuthenticationActivity.spotifyAccessToken;

public class LobbyActivity extends DaggerAppCompatActivity implements LobbyMVP.View, DialogInterface.OnCancelListener {

    @Inject
    LobbyMVP.Presenter presenter;

    @Inject
    Context appContext;

    LobbyJoinRoomDialog dialogFragment;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        setBackground();
        presenter.takeView(this);

        dialogFragment = new LobbyJoinRoomDialog();
        dialogFragment.setPresenter(presenter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setOnCancelListener(this);
    }

    private void setBackground() {
        Target layout = (ImageBackgroundLinearLayout) findViewById(R.id.linear_background_layout);
        Picasso.with(appContext).load(R.drawable.aux_background).into(layout);
    }

    public void onClickCreateRoom(View view) {
        presenter.onCreateRoomClick();
    }

    public void onClickExitBtn(View view) {
        navigateToLoginPage();
    }

    @Override
    public void showRoomCreationPopup() {
        ActivityUtil.showDialogFragment(this, dialogFragment);
    }

    @Override
    public void showLoad() {
        progressDialog.setMessage("Be Patient");
        progressDialog.show();
    }

    @Override
    public void dismissPopup() {
        dialogFragment.dismiss();
    }

    @Override
    public void navigateToLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(SPOTIFY_TOKEN_EXTRA, spotifyAccessToken);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_out_left);

        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.dropView();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        presenter.stopServiceCalls();
    }
}
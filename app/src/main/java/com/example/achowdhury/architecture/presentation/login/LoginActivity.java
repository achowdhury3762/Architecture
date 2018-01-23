package com.example.achowdhury.architecture.presentation.login;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.ViewPropertyAnimator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.achowdhury.architecture.R;
import com.example.achowdhury.architecture.util.AnimationUtils;
import com.example.achowdhury.architecture.util.picasso.ImageBackgroundLinearLayout;
import com.example.achowdhury.architecture.util.softkeyboard.KeyboardEditText;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.achowdhury.architecture.util.AnimationUtils.animateTranslation;

public class LoginActivity extends DaggerAppCompatActivity implements LoginMVP.View, EditTextKeyboardListener  {
    private static final int ANIMATION_DURATION = 300;
    private ImageView logoImageView;
    private LinearLayout inputEditTextLayout;
    private CardView signInButton;
    private KeyboardEditText usernameEditText;
    private KeyboardEditText passwordEditText;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    LoginMVP.Presenter presenter;

    @Inject
    Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        presenter.takeView(this);
        initializeViews();
        setBackground();
        keyboardListeners();
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.onResume();
    }

    private void keyboardListeners() {
        disposables = new CompositeDisposable();
        Disposable obs = RxView.focusChanges(usernameEditText)
                .subscribe(this);
        disposables.add(obs);

        Disposable obs2 = RxView.focusChanges(passwordEditText)
                .subscribe(this);
        disposables.add(obs2);

        passwordEditText.setKeyImeChangeListener(this);
        usernameEditText.setKeyImeChangeListener(this);
    }

    private void setBackground() {
        ImageBackgroundLinearLayout layout = (ImageBackgroundLinearLayout) findViewById(R.id.linear_background_layout);
        Picasso.with(appContext).load(R.drawable.aux_background).resize(50,50).into(layout);
    }

    private void initializeViews() {
        signInButton = (CardView) findViewById(R.id.sign_in_button);
        usernameEditText = (KeyboardEditText) findViewById(R.id.username_edit_text);
        passwordEditText = (KeyboardEditText) findViewById(R.id.password_edit_text);
        logoImageView = (ImageView) findViewById(R.id.aux_logo_image_view);
        inputEditTextLayout = (LinearLayout) findViewById(R.id.input_text_layout);
    }

    @Override
    public void showLoginFailed(LoginFailError error) {
        if (error == LoginFailError.INTERNETCONNECTIVITY) {
            Toast.makeText(getApplicationContext(), "Internet Connectivity Error", Toast.LENGTH_LONG).show();
        } else if (error == LoginFailError.INVALIDSIGNIN) {
            Toast.makeText(getApplicationContext(), "Invalid Signon", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Login Fail Error Occured", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showLoginSucceeded() {

    }

    @Override
    public void showKeyboardAnimationUp() {
        final float deltaY = -1 * (inputEditTextLayout.getTop() - logoImageView.getTop());

        AnimationUtils.fadeOutImage(logoImageView, ANIMATION_DURATION)
                .addListener(new AnimationUtils.EndAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateTranslation(inputEditTextLayout, 0, deltaY, ANIMATION_DURATION);
                    }
                });
    }

    ViewPropertyAnimator animator;
    
    @Override
    public void showKeyboardAnimationDown() {

        final float deltaY = (inputEditTextLayout.getTop() - logoImageView.getTop());

        animator = AnimationUtils.animateTranslation(inputEditTextLayout, 0, deltaY, ANIMATION_DURATION)
                .setListener(new AnimationUtils.EndAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        AnimationUtils.fadeInImage(logoImageView, ANIMATION_DURATION);
                        animator.setListener(null);
                    }
                });

    }

    public void onClickSignInButton() {
        EditText usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        EditText passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        presenter.checkLogin(username, password);
    }

    @Override
    public void accept(@NonNull Boolean keyboardFocus) throws Exception {
        presenter.onEditTextFocus(keyboardFocus);
    }

    @Override
    public void onKeyIme(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            presenter.onResume();

            passwordEditText.clearFocus();
            usernameEditText.clearFocus();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.dropView();
        disposables.clear();
    }
}
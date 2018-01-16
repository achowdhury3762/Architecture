package com.example.achowdhury.architecture.presentation.login;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.achowdhury.architecture.R;
import com.example.achowdhury.architecture.util.AnimationUtils;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LoginActivity extends DaggerAppCompatActivity implements LoginMVP.View {
    private static final int ANIMATION_DURATION = 300;
    private ImageView logoImageView;
    private LinearLayout inputEditTextLayout;
    private CardView signInButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CompositeDisposable disposables;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        presenter.takeView(this);
        initializeViews();

        disposables = new CompositeDisposable();

        Consumer<Object> consumer = new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                presenter.clickTextBox();
            }
        };

        Disposable sub = RxView.focusChanges(usernameEditText)
                .subscribe(consumer);
        disposables.add(sub);

        Disposable sub2 = RxView.focusChanges(passwordEditText)
                .subscribe(consumer);
        disposables.add(sub2);
    }

    private void initializeViews() {
        signInButton = (CardView) findViewById(R.id.sign_in_button);
        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
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
    public void pushTextBoxUp() {
        float logoXCoordinate = logoImageView.getLeft();
        float logoYCoordinate = logoImageView.getTop();

        float editTextXCoordinate = inputEditTextLayout.getLeft();
        float editTextYCoordinate = inputEditTextLayout.getTop();

        float deltaY = -1 * (editTextYCoordinate - logoYCoordinate);

        AnimationUtils.animateTranslation(inputEditTextLayout, 0, deltaY, ANIMATION_DURATION);
    }

    @Override
    public void onStop() {
        super.onStop();

        disposables.clear();
    }

    public void onClickSignInButton() {
        EditText usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        EditText passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        presenter.checkLogin(username, password);
    }
}
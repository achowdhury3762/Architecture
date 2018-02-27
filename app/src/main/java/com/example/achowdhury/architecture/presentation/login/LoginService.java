package com.example.achowdhury.architecture.presentation.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class LoginService {
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Inject
    public LoginService() {}

    public void login(String username, String password, final LoginResponseCallback listener) {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            listener.onLoginSuccess();
                        }
                        else
                            listener.onLoginFailure();
                    }
                });
    }

    public void registerUser(String username, String password) {
        auth.createUserWithEmailAndPassword(username, password);
    }

    interface LoginResponseCallback {
        void onLoginSuccess();

        void onLoginFailure();
    }
}

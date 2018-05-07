package com.example.achowdhury.architecture.presentation.playlist;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.achowdhury.architecture.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class PlaylistActivity extends DaggerAppCompatActivity {
    @Inject
    PlaylistPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout);

        RecyclerView rv = (RecyclerView) findViewById(R.id.)
    }
}

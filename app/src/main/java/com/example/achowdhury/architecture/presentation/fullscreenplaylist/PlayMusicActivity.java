package com.example.achowdhury.architecture.presentation.fullscreenplaylist;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.achowdhury.architecture.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class PlayMusicActivity extends DaggerAppCompatActivity {
    private ImageView imageView;

    @Inject
    PlayMusicPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 5;
//        Bitmap blurTemplate = BitmapFactory.decodeStream()

        imageView = (ImageView) findViewById(R.id.full_size_image);
//        imageView.setImageBitmap(blurTemplate);
//        imageView.setAlpha(100);
        Picasso.with(this).load(R.drawable.aux_background).fit().into(imageView);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                imageView.getBackground().setAlpha(0);
//            }
//        }, 3000);
    }
}
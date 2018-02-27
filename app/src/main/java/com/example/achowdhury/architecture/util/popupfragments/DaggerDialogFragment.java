package com.example.achowdhury.architecture.util.popupfragments;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.DialogFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class DaggerDialogFragment extends DialogFragment implements HasFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
}

package com.example.achowdhury.architecture.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import javax.annotation.Nonnull;

import dagger.internal.Preconditions;

public class ActivityUtil {
    public static void addFragmentToActivity(@Nonnull FragmentManager fragmentManager,
                               @NonNull Fragment fragment, int containerId) {
        Preconditions.checkNotNull(fragmentManager);
        Preconditions.checkNotNull(fragment);

        fragmentManager.beginTransaction().add(containerId, fragment).commit();
    }

    public static void showToast(String message, @NonNull Context context) {
        Preconditions.checkNotNull(context);
        context = context.getApplicationContext();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

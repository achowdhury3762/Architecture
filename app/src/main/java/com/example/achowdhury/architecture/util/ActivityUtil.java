package com.example.achowdhury.architecture.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    public static void hideKeyboard(Activity activity) {
        Preconditions.checkNotNull(activity);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}

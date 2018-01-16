package com.example.achowdhury.architecture.util;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

public class AnimationUtils {
    public static ValueAnimator animateImageGlow(final ImageView dullImage, final ImageView brightImage, int duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                brightImage.setAlpha((Float) animation.getAnimatedValue());
            }
        });

        animator.setDuration(duration);
        animator.start();

        return animator;
    }

    public static ViewPropertyAnimator animateTranslation(final View view, float deltaX, float deltaY, int duration) {
        ViewPropertyAnimator vpa = view.animate()
                .setDuration(duration)
                .translationXBy(deltaX)
                .translationYBy(deltaY);

        if (Build.VERSION.SDK_INT > 15) {
            vpa.withLayer();
        }
        return vpa;
    }

}

package com.example.achowdhury.architecture.util.softkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class KeyboardEditText extends android.support.v7.widget.AppCompatEditText {
    private KeyImeChange keyImeChangeListener;

    public KeyboardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setKeyImeChangeListener(KeyImeChange listener) {
        keyImeChangeListener = listener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyImeChangeListener != null) {
            keyImeChangeListener.onKeyIme(keyCode, event);
        }
        return false;
    }

    public interface KeyImeChange {
        void onKeyIme(int keyCode, KeyEvent event);
    }
}
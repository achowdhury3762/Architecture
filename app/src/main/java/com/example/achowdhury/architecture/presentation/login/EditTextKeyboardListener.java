package com.example.achowdhury.architecture.presentation.login;

import com.example.achowdhury.architecture.util.softkeyboard.KeyboardEditText;

import io.reactivex.functions.Consumer;

interface EditTextKeyboardListener extends Consumer<Boolean>, KeyboardEditText.KeyImeChange {

}

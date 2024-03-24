package com.mygdx.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

// processa gli input e li mette dentro una stringa

public class TextInputReader extends InputAdapter {
    private final StringBuilder inputText = new StringBuilder();

    @Override
    public boolean keyTyped(char character) {
        if(character != '\n' && character != '\b' && inputText.length() <= Commons.MAX_USERNAME_LENGTH)
            inputText.append(character);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACKSPACE && inputText.length() > 0) {
            inputText.deleteCharAt(inputText.length() - 1);
            return true;
        }
        return false;
    }

    public String getText(){
        return inputText.toString();
    }
}

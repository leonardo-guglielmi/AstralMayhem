package com.mygdx.utils;

import com.badlogic.gdx.Gdx;

public class TextInputProcessor {

    private TextInputReader reader;

    public boolean isReading(){
        return !(reader == null);
    }

    public void startReading(){
        if(reader == null){
            reader = new TextInputReader();
            Gdx.input.setInputProcessor(reader);
        }
    }

    public void stopReading(){
        Gdx.input.setInputProcessor(null);
        reader = null;
    }

    public String getText(){
        return reader.getText();
    }
}

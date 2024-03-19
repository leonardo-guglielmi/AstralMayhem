package com.mygdx.inputManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.gameStates.GameScreen;

public class KeyboardGameInputHandler implements InputHandler{

    private final Command pauseCmd;

    public KeyboardGameInputHandler(GameScreen gs){
        pauseCmd = new PauseCommand(gs);
    }

    @Override
    public void handle() {

        if(Gdx.input.isKeyJustPressed(Input.Keys.P))
            pauseCmd.execute();
    }
}

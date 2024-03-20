package com.mygdx.inputManagement;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.AstralMayhem;
import com.mygdx.gameStates.GameScreen;

public class KeyboardGameInputHandler implements InputHandler{

    private final Command pauseCmd;
    private final Command quitCmd;

    public KeyboardGameInputHandler(GameScreen gs, AstralMayhem g){
        pauseCmd = new PauseCommand(gs);
        quitCmd = new QuitCommand(g);
    }

    @Override
    public void handle() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P))
            pauseCmd.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
            quitCmd.execute();
    }
}

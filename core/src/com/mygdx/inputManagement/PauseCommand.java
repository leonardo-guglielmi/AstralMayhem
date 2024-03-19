package com.mygdx.inputManagement;

import com.mygdx.gameStates.GameScreen;

public class PauseCommand implements Command {

    GameScreen gs;

    public PauseCommand(GameScreen gs){
        this.gs = gs;
    }

    @Override
    public void execute(){
        if(gs.isPaused())
            gs.resume();
        else
            gs.pause();
    }
}

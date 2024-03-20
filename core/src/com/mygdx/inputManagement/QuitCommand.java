package com.mygdx.inputManagement;

import com.badlogic.gdx.Game;
import com.mygdx.AstralMayhem;
import com.mygdx.gameStates.GameScreen;

public class QuitCommand implements Command{

    AstralMayhem g;

    public QuitCommand(AstralMayhem g){
        this.g = g;
    }

    @Override
    public void execute(){
        g.setScreen(new GameScreen(g));
    }


}

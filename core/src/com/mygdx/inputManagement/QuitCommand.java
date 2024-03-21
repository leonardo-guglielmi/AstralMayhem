package com.mygdx.inputManagement;

import com.badlogic.gdx.Game;
import com.mygdx.AstralMayhem;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.gameStates.MenuScreen;

public class QuitCommand implements Command{

    AstralMayhem g;

    public QuitCommand(AstralMayhem g){
        this.g = g;
    }

    @Override
    public void execute(){
        g.setScreen(new MenuScreen(g));
    }


}

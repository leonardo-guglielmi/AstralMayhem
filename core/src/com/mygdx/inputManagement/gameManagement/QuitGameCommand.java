package com.mygdx.inputManagement.gameManagement;

import com.mygdx.AstralMayhem;
import com.mygdx.gameStates.MenuScreen;
import com.mygdx.inputManagement.Command;

public class QuitGameCommand implements Command {

    AstralMayhem g;

    public QuitGameCommand(AstralMayhem g){
        this.g = g;
    }

    @Override
    public void execute(){
        g.setScreen(new MenuScreen(g));
    }


}

package com.mygdx.inputManagement.menuManagement;

import com.badlogic.gdx.Screen;
import com.mygdx.AstralMayhem;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.inputManagement.Command;

public class StartGameCommand implements Command {

    AstralMayhem game;

    public StartGameCommand(AstralMayhem game){
        this.game = game;
    }

    @Override
    public void execute() {
        Screen tmp = game.getScreen();
        game.setScreen(new GameScreen(game));
        tmp.dispose();
    }
}

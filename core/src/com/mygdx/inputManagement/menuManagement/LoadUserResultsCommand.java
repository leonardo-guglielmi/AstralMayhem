package com.mygdx.inputManagement.menuManagement;

import com.mygdx.gameStates.MenuScreen;
import com.mygdx.inputManagement.Command;


public class LoadUserResultsCommand implements Command {

    private final MenuScreen ms;

    public LoadUserResultsCommand(MenuScreen ms){
        this.ms = ms;
    }

    @Override
    public void execute() {
        ms.loadUserScore();
    }
}
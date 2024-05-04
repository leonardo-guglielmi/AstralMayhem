package com.mygdx.inputManagement.menuManagement;

import com.mygdx.gameStates.MenuScreen;
import com.mygdx.inputManagement.Command;


public class  LoadBestResultsCommand implements Command {

    private final MenuScreen ms;

    public LoadBestResultsCommand(MenuScreen ms){
        this.ms = ms;
    }

    @Override
    public void execute() {
        ms.loadBestScore();
    }
}

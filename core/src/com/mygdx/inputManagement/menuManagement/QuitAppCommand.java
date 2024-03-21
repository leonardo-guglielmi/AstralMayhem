package com.mygdx.inputManagement.menuManagement;

import com.badlogic.gdx.Gdx;
import com.mygdx.inputManagement.Command;

public class QuitAppCommand implements Command {

    @Override
    public void execute(){
        Gdx.app.exit();
    }
}

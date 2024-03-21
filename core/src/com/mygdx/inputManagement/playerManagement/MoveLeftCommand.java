package com.mygdx.inputManagement.playerManagement;

import com.mygdx.entities.Hero;
import com.mygdx.inputManagement.Command;

public class MoveLeftCommand implements Command {

    private final Hero player;

    public MoveLeftCommand(Hero player){
        this.player = player;
    }

    @Override
    public void execute( ) {
        player.move(-1,0);
    }
}

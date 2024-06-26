package com.mygdx.inputManagement.playerManagement;

import com.mygdx.entities.Hero;
import com.mygdx.inputManagement.Command;

public class MoveRightCommand implements Command {
    private final Hero player;

    public MoveRightCommand(Hero player){
        this.player = player;
    }

    @Override
    public void execute( ) {
        player.move(1,0);
    }
}

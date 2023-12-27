package com.mygdx.inputManagement;

import com.mygdx.entities.Hero;

public class MoveLeftCommand implements Command{

    private final Hero player;

    public MoveLeftCommand(Hero player){
        this.player = player;
    }

    @Override
    public void execute( ) {
        player.move(-3,0);
    }
}

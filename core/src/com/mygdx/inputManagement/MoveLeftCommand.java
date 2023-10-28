package com.mygdx.inputManagement;

import com.mygdx.Hero;

public class MoveLeftCommand implements Command{

    private final Hero player;

    public MoveLeftCommand(Hero player){
        this.player = player;
    }

    @Override
    public void execute( ) {
        player.move(-5);
    }
}

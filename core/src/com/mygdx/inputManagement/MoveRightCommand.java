package com.mygdx.inputManagement;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.characters.Hero;

public class MoveRightCommand implements Command{
    private final Hero player;

    public MoveRightCommand(Hero player){
        this.player = player;
    }

    @Override
    public void execute( ) {
        player.move(new Vector2(3,0));
    }
}

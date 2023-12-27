package com.mygdx.inputManagement;

import com.mygdx.entities.Hero;

public class ShootCommand implements Command{
    private final Hero player;

    public ShootCommand(Hero player){
        this.player = player;
    }
    @Override
    public void execute() {
        player.shoot();
    }
}

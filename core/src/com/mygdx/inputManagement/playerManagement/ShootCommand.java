package com.mygdx.inputManagement.playerManagement;

import com.mygdx.entities.Hero;
import com.mygdx.inputManagement.Command;

public class ShootCommand implements Command {
    private final Hero player;

    public ShootCommand(Hero player){
        this.player = player;
    }
    @Override
    public void execute() {
        player.shoot();
    }
}

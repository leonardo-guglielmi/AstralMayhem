package com.mygdx.enemyLogic;

import com.mygdx.utils.Timer;
import com.mygdx.entities.Enemy;
import com.mygdx.entities.Hero;

public class AdvancedEnemyStrategy implements Strategy{

    private final Enemy e;
    private final Hero h;
    private final Timer shootTimer;
    private final Timer teleportTimer;
    private int dirY = 0;
    public AdvancedEnemyStrategy(Enemy e, Hero h, int shootTime, int teleportTime){
        this.e = e;
        this.h = h;
        shootTimer = new Timer(shootTime);
        teleportTimer  = new Timer(teleportTime);

    }
    @Override
    public void execute() {
        int dirX = 0;

        if(h.getX() -e.getX() > 0)
            dirX = 2;
        else if(h.getX() -e.getX() < 0)
            dirX = -2;

        if(teleportTimer.check())
            dirY = -100;
        else if(dirY != 0)
            dirY = 0;

        e.move(dirX, dirY);

        if(shootTimer.check())
            e.shoot();
    }
}

package com.mygdx.enemyLogic;

import com.mygdx.Timer;
import com.mygdx.characters.Enemy;
import com.mygdx.characters.Hero;

public class AdvancedEnemyStrategy implements Strategy{

    private final Enemy e;
    private final Hero h;
    private final Timer shootTimer;
    private final Timer teleportTimer = new Timer(10);
    private int dirX = 1;
    private int dirY = 0;
    public AdvancedEnemyStrategy(Enemy e, Hero h, int t){
        this.e = e;
        this.h = h;
        this.shootTimer = new Timer(t);
    }
    @Override
    public void execute() {
        dirX = 0;
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

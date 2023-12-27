package com.mygdx.enemyLogic;

import com.mygdx.Commons;
import com.mygdx.Timer;
import com.mygdx.entities.Enemy;

public class BaseEnemyStrategy implements Strategy{
    private final Enemy e;
    private int dirX = 1;
    private int dirY = 0;
    private final Timer shootTimer;

    public BaseEnemyStrategy(Enemy e, int t){
        this.e = e;
        this.shootTimer = new Timer(t);
    }

    @Override
    public void execute() {
        if(e.getX() >= Commons.WORLD_X_END -e.getWidth() || e.getX() < Commons.WORLD_X_START){
            dirX *= -1;
            dirY = -100;
        }
        else if(dirY != 0){
            dirY = 0;
        }
        e.move(dirX, dirY);

        if(shootTimer.check())
            e.shoot();
    }
}

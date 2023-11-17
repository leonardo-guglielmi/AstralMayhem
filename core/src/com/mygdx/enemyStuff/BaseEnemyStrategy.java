package com.mygdx.enemyStuff;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Commons;
import com.mygdx.Timer;
import com.mygdx.characters.Enemy;
import com.mygdx.gameStates.TestScreen;

public class BaseEnemyStrategy implements Strategy{
    private final Enemy e;
    private int dirX = 1;
    private int dirY = 0;
    private final Timer shootTimer;

    private boolean isDescending = false;
    public BaseEnemyStrategy(Enemy e, int t){
        this.e = e;
        this.shootTimer = new Timer(t);
    }

    @Override
    public void execute() {
        if(e.getX() >= Commons.HORIZONTAL_END -e.getWidth() || e.getX() < Commons.HORIZONTAL_START){
            dirX *= -1;
            dirY = -100;
        }
        else{
            dirY = 0;
        }
        e.move(dirX, dirY);

        if(shootTimer.check())
            e.shoot();
    }
}

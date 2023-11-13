package com.mygdx.enemyStuff;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Timer;
import com.mygdx.characters.Enemy;

public class BaseEnemyStrategy implements Strategy{
    private final Enemy e;
    private Vector2 versor = new Vector2(1,0);
    private final Timer shootTimer;

    private boolean isDescending = false;
    public BaseEnemyStrategy(Enemy e, int t){
        this.e = e;
        this.shootTimer = new Timer(t);
    }

    @Override
    public void execute() {
        if(e.getX() > 1000-64 || e.getX() < 0){
            versor.x *= -1;
            e.teleport();
        }
        e.move(versor);

        if(shootTimer.check())
            e.shoot();
    }
}

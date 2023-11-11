package com.mygdx.enemyStuff;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Timer;
import com.mygdx.characters.Enemy;

public class BaseEnemyStrategy implements Strategy{
    private final Enemy e;
    private Vector2 versor = new Vector2(1,0);
    private final Timer timer;

    public BaseEnemyStrategy(Enemy e, int t){
        this.e = e;
        this.timer = new Timer(t);
    }

    @Override
    public void execute() {
        if(e.getX() > 860)
            versor.x = -1;
        if(e.getX() < 0)
            versor.x = 1;
        if((e.getX() > 860 || e.getX() <0) && e.getY()%50 == 0)
            versor.y -= 1;
        e.move(versor);

        if(timer.check())
            e.shoot();
    }
}

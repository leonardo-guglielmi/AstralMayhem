package com.mygdx.enemyStuff;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Timer;
import com.mygdx.characters.Enemy;
import com.mygdx.characters.Hero;

public class AdvancedEnemyStrategy implements Strategy{

    private final Enemy e;
    private final Hero h;
    private final Timer shootTimer;
    private final Timer teleportTimer = new Timer(10);
    private Vector2 versor = new Vector2(0,0);
    public AdvancedEnemyStrategy(Enemy e, Hero h, int t){
        this.e = e;
        this.h = h;
        this.shootTimer = new Timer(t);
    }
    @Override
    public void execute() {
        if(h.getX() -e.getX() > 0)
            versor.x = 5;
        else if(h.getX() -e.getX() < 0)
            versor.x = -5;

        e.move(versor);
        if(shootTimer.check())
            e.shoot();
    }
}

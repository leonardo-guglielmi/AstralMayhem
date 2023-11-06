package com.mygdx.enemyStuff;

import com.mygdx.characters.Enemy;

public class BaseEnemyStrategy implements Strategy{
    private Enemy e;

    public BaseEnemyStrategy(Enemy e){
        this.e = e;
    }

    @Override
    public void execute() {
        e.move();
        e.shoot();
    }
}

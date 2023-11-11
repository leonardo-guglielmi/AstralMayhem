package com.mygdx.enemyStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.characters.Enemy;
import com.mygdx.manager.BulletManager;

public class EnemyFactory implements AbstractEnemyFactory{
    private BulletManager bm;

    public EnemyFactory(BulletManager bm){
        this.bm = bm;
    }
    @Override
    public Enemy createBaseEnemy() {
        Enemy e = new Enemy(new Texture(Gdx.files.internal("enemy.png")), 0, 500, bm);
        e.setStrategy(new BaseEnemyStrategy(e, 3));
        return e;
    }
}

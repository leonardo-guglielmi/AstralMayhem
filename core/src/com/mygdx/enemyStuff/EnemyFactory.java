package com.mygdx.enemyStuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.characters.Enemy;
import com.mygdx.characters.Hero;
import com.mygdx.manager.BulletManager;

public class EnemyFactory implements AbstractEnemyFactory{
    private BulletManager bm;
    private Hero h;

    public EnemyFactory(BulletManager bm, Hero h){
        this.bm = bm;
        this.h = h;
    }
    @Override
    public Enemy createBaseEnemy() {
        Enemy e = new Enemy(new Texture(Gdx.files.internal("enemy.png")), 0, 500, bm);
        e.setStrategy(new BaseEnemyStrategy(e, 3));
        return e;
    }

    @Override
    public Enemy createAdvanceEnemy(){
        Enemy e = new Enemy(new Texture(Gdx.files.internal("advanced_enemy.png")), 0, 500, bm);
        e.setStrategy(new AdvancedEnemyStrategy(e, h, 3));
        return e;
    }
}

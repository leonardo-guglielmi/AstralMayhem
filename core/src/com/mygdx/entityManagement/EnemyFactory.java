package com.mygdx.entityManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.utils.Commons;
import com.mygdx.entities.Enemy;
import com.mygdx.entities.Hero;
import com.mygdx.enemyLogic.AdvancedEnemyStrategy;
import com.mygdx.enemyLogic.BaseEnemyStrategy;

public class EnemyFactory{
    private BulletManager bm;
    private Hero h;

    public EnemyFactory(BulletManager bm, Hero h){
        this.bm = bm;
        this.h = h;
    }

    public Enemy createBaseEnemy() {
        Enemy e = new Enemy(new Texture(Gdx.files.internal("entities/enemy.png")), Commons.WORLD_X_START, 500, bm);
        e.setStrategy(new BaseEnemyStrategy(e, 3));
        return e;
    }

    public Enemy createAdvanceEnemy(){
        Enemy e = new Enemy(new Texture(Gdx.files.internal("entities/advanced_enemy.png")), Commons.WORLD_X_START, 500, bm);
        e.setStrategy(new AdvancedEnemyStrategy(e, h, 3));
        return e;
    }
}

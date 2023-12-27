package com.mygdx.entityManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.Commons;
import com.mygdx.characters.Enemy;
import com.mygdx.characters.Hero;
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
        Enemy e = new Enemy(new Texture(Gdx.files.internal("enemy.png")), Commons.WORLD_X_START, 500, bm);
        e.setStrategy(new BaseEnemyStrategy(e, 3));
        return e;
    }

    public Enemy createAdvanceEnemy(){
        Enemy e = new Enemy(new Texture(Gdx.files.internal("advanced_enemy.png")), Commons.WORLD_X_START, 500, bm);
        e.setStrategy(new AdvancedEnemyStrategy(e, h, 3));
        return e;
    }
}

package com.mygdx.entities;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.utils.Commons;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.enemyLogic.Strategy;
import com.mygdx.enemyLogic.StrategyContext;

public class Enemy implements Character, StrategyContext {
    private final Rectangle body = new Rectangle();
    private final BulletManager bm;
    private final TypeOfEntity typeEntity = TypeOfEntity.ENEMY;
    private TypeOfEnemy typeEnemy;
    private Strategy strat;

    public Enemy(int startingX, int startingY, BulletManager bm, TypeOfEnemy typeEnemy){
        body.height = Commons.ENEMY_HEIGHT;
        body.width = Commons.ENEMY_WIDTH;
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
        this.typeEnemy = typeEnemy;
    }

    public float getX(){
        return body.x;
    }

    public float getY(){
        return body.y;
    }

    public int getWidth(){
        return (int)body.width;
    }

    @Override
    public void move(int dx, int dy) {
        body.x += dx;
        body.y += dy;
        if (body.x < Commons.WORLD_X_START -1)
            body.x = Commons.WORLD_X_START;
        if (body.x > Commons.WORLD_X_END -body.width)
            body.x = Commons.WORLD_X_END -body.width;
    }

    @Override
    public void shoot() {
        bm.addBullet(body.x + body.width /2,body.y + body.height /2, -3, typeEntity);
    }

    @Override
    public int getNumCollisions() {
        return bm.getBulletCollision(body, typeEntity);
    }

    @Override
    public void setStrategy(Strategy strat){
        this.strat = strat;
    }

    public void update(){
        strat.execute();
    }

    public TypeOfEnemy getTypeOfEnemy(){
        return typeEnemy;
    }
}

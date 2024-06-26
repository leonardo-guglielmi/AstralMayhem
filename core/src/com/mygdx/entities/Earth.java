package com.mygdx.entities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.observers.Subject;
import com.mygdx.observers.ObserverHandler;
import com.mygdx.observers.Observer;
import com.mygdx.utils.Commons;

public class Earth implements Subject {
    private int hp  = 100;
    private final TypeOfEntity type = TypeOfEntity.EARTH;
    private final Rectangle body = new Rectangle();
    private final BulletManager bm;
    private final ObserverHandler obs = new ObserverHandler();
    private final GameScreen gs;

    public Earth(BulletManager bm, int startX, int startY, GameScreen gs){
        body.x = startX;
        body.y = startY;
        body.width = Commons.EARTH_WIDTH;
        body.height = Commons.EARTH_HEIGHT;
        this.bm = bm;
        this.gs = gs;
    }

    public int getHp(){
        return hp;
    }

    public float getX(){
        return body.x;
    }

    public float getY(){
        return body.y;
    }

    public void update(){
        int damage = bm.getBulletCollision(body, type);
        hp -= damage;
        if(damage > 0)
            gs.updateScore(-10 *damage);
        if(hp <= 0){
            obs.notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer o){
        obs.addObserver(o);
    }
}

package com.mygdx.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.observers.Observable;
import com.mygdx.observers.Subject;
import com.mygdx.observers.Observer;

public class Earth implements Observable {
    private int hp  = 100;
    private final TypeOfEntity type = TypeOfEntity.EARTH;
    private final Rectangle body = new Rectangle();
    private final BulletManager bm;
    private final Subject obs = new Subject();

    public Earth(Texture tx, BulletManager bm, int startX, int startY){
        body.x = startX;
        body.y = startY;
        body.width = tx.getWidth();
        body.height = tx.getHeight();
        this.bm = bm;
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
            GameScreen.score -= 10L *damage;
        if(hp <= 0){
            obs.notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer o){
        obs.addObserver(o);
    }
}

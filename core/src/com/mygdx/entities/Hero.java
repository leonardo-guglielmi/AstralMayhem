package com.mygdx.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Commons;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.inputManagement.PlayerInputHandler;
import com.mygdx.observers.Observable;
import com.mygdx.observers.Observed;
import com.mygdx.observers.Observer;

/**
 * This class contains all the information about the Hero character
 */

public class Hero implements Character, Observable{
    private int hp = 1;
    private int speed = 2;
    private TypeOfEntity type = TypeOfEntity.HERO;
    private Rectangle body = new Rectangle();
    private final PlayerInputHandler input = new PlayerInputHandler(this);
    private BulletManager bm;
    private Observed obs = new Observed();


    public Hero(AssetManager am, int startingX, int startingY, BulletManager bm) {
        body.height = am.<Texture>get("entities/hero.png").getHeight();
        body.width = am.<Texture>get("entities/hero.png").getWidth();
        // il rectangle di libgdx prende come riferimento x,y l'angolo in basso a sinistra
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
    }

    public int getHp() {
        return hp;
    }

    public float getX(){
        return body.x;
    }

    public float getY(){
        return body.y;
    }

    @Override
    public void move(int dx, int dy) {
        body.x += dx * speed;
        if (body.x < Commons.WORLD_X_START)
            body.x = Commons.WORLD_X_START;
        if (body.x > Commons.WORLD_X_END -body.width)
            body.x = Commons.WORLD_X_END -body.width;
    }

    @Override
    public void shoot() {
        bm.addBullet((int) (body.x + body.width / 4), (int) (body.y + body.height / 2), 10, type);
    }

    @Override
    public int getNumCollisions() {
        return bm.getBulletCollision(body, type);
    }

    public void update(){
        input.handle();
        int damage = getNumCollisions();
        if(damage > 0){
            hp -= damage;
            obs.notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer o){
        obs.addObserver(o);
    }
}
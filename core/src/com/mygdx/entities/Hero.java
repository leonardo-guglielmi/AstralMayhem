package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Commons;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.displayable.Displayable;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.inputManagement.PlayerInputHandler;
import com.mygdx.observers.Observable;
import com.mygdx.observers.ObservePoint;
import com.mygdx.observers.Observer;

/**
 * This class contains all the information about the Hero character
 */

public class Hero implements Character, Displayable, Observable{
    private int hp = 1;
    private int speed = 2;
    private TypeOfEntity type = TypeOfEntity.HERO;
    private Texture tx;
    private Rectangle body = new Rectangle();
    private final PlayerInputHandler input = new PlayerInputHandler(this);
    private BulletManager bm;
    private ObservePoint obs = new ObservePoint();


    public Hero(Texture tx, int startingX, int startingY, BulletManager bm) {
        this.tx = tx;
        body.height = tx.getHeight();
        body.width = tx.getWidth();
        // il rectangle di libgdx prende come riferimento x,y l'angolo in basso a sinistra
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
    }

    public int getHp() {
        return hp;
    }

    public int getX(){
        return (int)body.x;
    }

    @Override
    public DisplayableObject getDisplayableObject() {
        return new DisplayableObject(tx, (int) body.x, (int) body.y);
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
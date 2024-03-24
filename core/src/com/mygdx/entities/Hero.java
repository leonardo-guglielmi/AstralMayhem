package com.mygdx.entities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.inputManagement.InputHandler;
import com.mygdx.inputManagement.playerManagement.KeyboardPlayerInputHandler;
import com.mygdx.utils.Commons;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.observers.Observable;
import com.mygdx.observers.Subject;
import com.mygdx.observers.Observer;

/**
 * This class contains all the information about the Hero character
 */

public class Hero implements Character, Observable{
    private int hp = 10;
    private final static int SPEED = 10;
    private final TypeOfEntity type = TypeOfEntity.HERO;
    private final Rectangle body = new Rectangle();
    private final InputHandler input;
    private final BulletManager bm;
    private final Subject obs = new Subject();


    public Hero(int startingX, int startingY, BulletManager bm) {
        body.height =  Commons.HERO_HEIGHT;
        body.width = Commons.HERO_WIDTH;
        // il rectangle di libgdx prende come riferimento x,y l'angolo in basso a sinistra
        body.x = startingX;
        body.y = startingY;
        input = new KeyboardPlayerInputHandler(this);
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
        body.x += dx * SPEED;
        if (body.x < Commons.WORLD_X_START)
            body.x = Commons.WORLD_X_START;
        if (body.x > Commons.WORLD_X_END -body.width)
            body.x = Commons.WORLD_X_END -body.width;
    }

    @Override
    public void shoot() {
        bm.addBullet(body.x +body.width/2 - (float) Commons.BULLET_WIDTH /2, body.y +body.height, 10, type);
    }

    @Override
    public int getNumCollisions() {
        return bm.getBulletCollision(body, type);
    }

    public void update(){
        input.handle();
        int damage = getNumCollisions();
        if(damage > 0)
            hp -= damage;
        if(hp <= 0)
            obs.notifyObservers();
    }

    @Override
    public void addObserver(Observer o){
        obs.addObserver(o);
    }
}
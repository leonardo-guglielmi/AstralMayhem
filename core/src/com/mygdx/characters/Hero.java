package com.mygdx.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.displayable.DisplayObject;
import com.mygdx.displayable.Displayable;
import com.mygdx.manager.BulletManager;
import com.mygdx.inputManagement.PlayerInputHandler;

public class Hero implements Character, Displayable {
    private int hp = 10;
    private Texture tx;
    private Rectangle body = new Rectangle();
    private PlayerInputHandler input = new PlayerInputHandler(this);
    private int speed = 2;

    private BulletManager bm;

    private TypeOfCharacter type = TypeOfCharacter.HERO;


    public Hero(Texture tx, int startingX, int startingY, BulletManager bm){
        this.tx = tx;
        body.height = tx.getHeight();
        body.width = tx.getWidth();
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public DisplayObject getDisplayObject(){
        return new DisplayObject(tx, (int)body.x, (int)body.y);
    }

    public void handleInput(){
        input.handle();
    }

    @Override
    public void move(int dir) {
        body.x += dir * speed;
        if (body.x < 0)
            body.x = 0;
        if (body.x > 860)
            body.x = 860;
    }

    @Override
    public void move() {
        this.move(0);
    }

    public void shoot(){
        bm.addBullet((int)(body.x+body.width/2), (int)(body.y+body.height/2), 10, type);
    }

    @Override
    public boolean checkCollision() {
        int damage = bm.getBulletCollision(body, type);
        boolean res = damage >= 1;
        hp -= damage;
        return res;
    }
}

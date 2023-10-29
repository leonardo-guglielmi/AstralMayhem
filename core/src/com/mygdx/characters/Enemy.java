package com.mygdx.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.displayable.DisplayObject;
import com.mygdx.displayable.Displayable;
import com.mygdx.bullets.BulletManager;

public class Enemy implements Character, Displayable {

    private Texture tx;
    private Rectangle body = new Rectangle();
    private int speed = 2;
    private BulletManager bm;
    private TypeOfCharacter type = TypeOfCharacter.ENEMY;

    public Enemy(Texture tx, int startingX, int startingY, BulletManager bm){
        this.tx = tx;
        body.height = tx.getHeight();
        body.width = tx.getWidth();
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
    }

    @Override
    public DisplayObject getDisplayObject(){
        return new DisplayObject(tx, (int)body.x, (int)body.y);
    }

    @Override
    public void move() {
        this.move(1);
    }


    //todo: questa devo assicurarmi che non venga usata
    @Override
    public void move(int dir) {
        body.x += dir * speed;
        if (body.x < 0) {
            body.x = 0;
            speed = 1;
        }
        if (body.x > 860) {
            body.x = 860;
            speed = -1;
        }
    }

    @Override
    public void shoot() {

    }

    @Override
    public boolean checkCollision() {
        return bm.getBulletCollision(body, type) >= 1;
    }
}

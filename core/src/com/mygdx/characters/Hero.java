package com.mygdx.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.bullets.BulletManager;
import com.mygdx.bullets.TypeOfCharacter;
import com.mygdx.inputManagement.PlayerInputHandler;

public class Hero implements Character{
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

    public int getPosX() {
        return (int)body.x;
    }

    public int getPosY() {
        return (int)body.y;
    }

    public Texture getTexture() {
        return tx;
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
        bm.addBullet((int)body.x, (int)(body.y+body.height/2), 500, type);
    }

    @Override
    public void checkCollision() {

    }
}

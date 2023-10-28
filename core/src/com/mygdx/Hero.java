package com.mygdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.inputManagement.PlayerInputHandler;

public class Hero {
    public int hp = 10;
    public Texture tx;
    public Rectangle body = new Rectangle();
    public PlayerInputHandler input = new PlayerInputHandler(this);

    public Hero(Texture tx, int startingX, int startingY){
        this.tx = tx;
        body.height = tx.getHeight();
        body.width = tx.getWidth();
        body.x = startingX;
        body.y = startingY;
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

    public void move(int dir){
        body.x += dir;
    }
}

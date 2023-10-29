package com.mygdx.bullets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Rectangle body = new Rectangle();
    private Texture tx;
    private int vel;
    private TypeOfCharacter source;


    public Bullet(int startX, int startY, int vel, TypeOfCharacter source){
        tx = new Texture(Gdx.files.internal("bullet.png"));
        body.width = 8;
        body.height = 8;
        body.x = startX;
        body.y = startY;
        this.vel = vel;
        this.source = source;
    }

    public Texture getTexture(){
        return tx;
    }

    public int getPosX(){
        return (int)body.x;
    }

    public int getPosY(){
        return (int)body.y;
    }

    public TypeOfCharacter getSource(){
        return source;
    }

    public void updatePosition(float delta){
        body.y += vel*delta;
    }
}

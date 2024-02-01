package com.mygdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * This class contains all the information about on bullet
 */
public class Bullet {
    private final TypeOfEntity source;
    private int vel;
    private Rectangle body = new Rectangle();
    private final Texture tx;


    public Bullet(int startX, int startY, int vel, TypeOfEntity source){
        tx = new Texture(Gdx.files.internal("entities/bullet.png"));
        body.width = tx.getWidth();
        body.height = tx.getHeight();
        body.x = startX;
        body.y = startY;
        this.vel = vel;
        this.source = source;
    }

    public float getY(){
        return body.y;
    }

    public float getX(){
        return body.x;
    }

    public TypeOfEntity getSource(){
        return source;
    }

    /**
     * This method returns a copy of the body object, in this way the state of the bullet's body cannot be changed
     * @return body object copy
     */
    public Rectangle getBody(){
        return new Rectangle(body);
    }

    public void updatePosition(){
        body.y += vel;
    }

    /**
     * Check if the entityBody given as parameter collide with the bullet's entityBody, and if there is a mismatch between the type
     * of bodies returns true, which indicates that the event of collision is realized
     * @param entityBody the entityBody of the object that is colliding with the bullet
     * @param type the type of object that is colliding with the bullet
     * @return the state of collision
     */
    public boolean isHitting(Rectangle entityBody, TypeOfEntity type){
        return body.overlaps(entityBody) && source != type;
    }
}
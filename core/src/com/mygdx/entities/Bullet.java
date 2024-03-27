package com.mygdx.entities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.utils.Commons;

/**
 * This class contains all the information about on bullet
 */
public class Bullet {
    private final TypeOfEntity source;
    private final int vel;
    private final Rectangle body = new Rectangle();


    public Bullet(float startX, float startY, int vel, TypeOfEntity source){
        body.height = Commons.BULLET_HEIGHT;
        body.width = Commons.BULLET_WIDTH;
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

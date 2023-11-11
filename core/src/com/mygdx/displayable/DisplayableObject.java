package com.mygdx.displayable;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class is used to encapsulate all and only the information necessary to render an object, in this way the state
 * of the source object cannot be altered.
 */
public class DisplayableObject {
    public Texture tx;
    public int posX;
    public int posY;

    public DisplayableObject(Texture tx, int posX, int posY){
        this.tx = tx;
        this.posX = posX;
        this.posY = posY;
    }
}

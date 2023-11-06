package com.mygdx.displayable;

import com.badlogic.gdx.graphics.Texture;

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

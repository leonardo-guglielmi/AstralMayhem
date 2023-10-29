package com.mygdx.displayable;

import com.badlogic.gdx.graphics.Texture;

public class DisplayObject {
    public Texture tx;
    public int posX;
    public int posY;

    public DisplayObject(Texture tx, int posX, int posY){
        this.tx = tx;
        this.posX = posX;
        this.posY = posY;
    }
}

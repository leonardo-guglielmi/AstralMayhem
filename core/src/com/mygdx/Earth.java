package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.displayable.Displayable;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.entityManagement.BulletManager;

public class Earth implements Displayable {
    private Texture tx;
    private Rectangle body = new Rectangle();
    private int hp  = 10;
    private final BulletManager bm;
    private TypeOfEntity type = TypeOfEntity.EARTH;

    public Earth(Texture tx, BulletManager bm, int startX, int startY){
        this.tx = tx;
        body.x = startX;
        body.y = startY;
        body.width = tx.getWidth();
        body.height = tx.getHeight();
        this.bm = bm;
    }

    public int getHp(){
        return hp;
    }

    public void update(){
        hp -= bm.getBulletCollision(body, type);
    }

    @Override
    public DisplayableObject getDisplayableObject() {
        return new DisplayableObject(tx, (int)body.x, (int)body.y);
    }

    @Override
    public void disposeTexture() {

    }
}

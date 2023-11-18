package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.displayable.Displayable;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.observer.Observed;
import com.mygdx.observer.Observer;

public class Earth implements Displayable {
    private Texture tx;
    private Rectangle body = new Rectangle();
    private int hp  = 1000;
    private final BulletManager bm;
    private TypeOfEntity type = TypeOfEntity.EARTH;
    private Observed obs = new Observed();

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
        int damage = bm.getBulletCollision(body, type);
        if(damage > 0){
            hp -= damage;
            obs.notifyObservers();
        }
    }

    @Override
    public DisplayableObject getDisplayableObject() {
        return new DisplayableObject(tx, (int)body.x, (int)body.y);
    }

    @Override
    public void disposeTexture() {

    }

    public void addObserver(Observer o){
        obs.addObserver(o);
    }
}

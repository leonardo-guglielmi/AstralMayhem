package com.mygdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.displayable.Displayable;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.observers.Observable;
import com.mygdx.observers.Observed;
import com.mygdx.observers.Observer;

public class Earth implements Displayable, Observable {
    private int hp  = 1000;
    private final TypeOfEntity type = TypeOfEntity.EARTH;
    private Texture tx;
    private final Rectangle body = new Rectangle();
    private final BulletManager bm;
    private final Observed obs = new Observed();

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

    @Override
    public DisplayableObject getDisplayableObject() {
        return new DisplayableObject(tx, (int)body.x, (int)body.y);
    }
    public void update(){
        hp -= bm.getBulletCollision(body, type);
        if(hp <= 0){
            obs.notifyObservers();
        }
    }

    @Override
    public void addObserver(Observer o){
        obs.addObserver(o);
    }
}

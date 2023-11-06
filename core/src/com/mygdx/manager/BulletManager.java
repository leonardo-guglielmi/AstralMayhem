package com.mygdx.manager;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Bullet;
import com.mygdx.characters.TypeOfCharacter;
import com.mygdx.displayable.DisplayObject;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class BulletManager implements Manager{
    private ArrayList<Bullet> bulletSet = new ArrayList<>();

    public void addBullet(int startX, int startY, int vel, TypeOfCharacter source) {
        bulletSet.add(new Bullet(startX, startY, vel, source));
    }

    @Override
    public void update(){
        for(Bullet b : bulletSet)
            b.updatePosition();
        this.checkBorders();
    }

    @Override
    //todo: vedi se passare la texture per copia o lasciarla per reference
    public ArrayList<DisplayObject> getDisplayable(){
        ArrayList<DisplayObject> arrDisp = new ArrayList<>();
        for(Bullet b : bulletSet)
            arrDisp.add(b.getDisplayObject());
        return arrDisp;
    }

    public int getBulletCollision(Rectangle body, TypeOfCharacter type){
        int count = 0;
        Iterator<Bullet> iter = bulletSet.iterator();
        while(iter.hasNext()) {
            Bullet b = iter.next();
            if(b.isHitting(body, type)) {
                iter.remove();
                count++;
            }
        }
        return count;
    }

    private void checkBorders(){
        Iterator<Bullet> iter = bulletSet.iterator();
        while (iter.hasNext()){
            Bullet b = iter.next();
            if(b.getPosY() > 700 || b.getPosY() < 0)
                iter.remove();
        }
    }
}

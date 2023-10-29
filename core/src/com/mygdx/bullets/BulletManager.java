package com.mygdx.bullets;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.characters.TypeOfCharacter;
import com.mygdx.displayable.DisplayObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class BulletManager {
    private HashSet<Bullet> bulletSet = new HashSet<>();

    public void addBullet(int startX, int startY, int vel, TypeOfCharacter source) {
        bulletSet.add(new Bullet(startX, startY, vel, source));
    }

    // update the position of all bullets
    public void update(){
        Iterator<Bullet> iter = bulletSet.iterator();
        while(iter.hasNext()){
            Bullet b = iter.next();
            b.updatePosition();
            if(b.getPosY() > 700 || b.getPosY() < 0)
                iter.remove();
        }
    }

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

}

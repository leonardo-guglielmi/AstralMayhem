package com.mygdx.manager;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Bullet;
import com.mygdx.characters.TypeOfCharacter;
import com.mygdx.displayable.DisplayableObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BulletManager implements Manager{
    private Set<Bullet> bulletSet = new HashSet<>();

    public void addBullet(int startX, int startY, int vel, TypeOfCharacter source) {
        bulletSet.add(new Bullet(startX, startY, vel, source));
    }

    @Override
    public void update(){
        for(Bullet b : bulletSet)
            b.updatePosition();
        this.checkBorders();
        this.checkMutualCollisions();
    }

    @Override
    //todo: vedi se passare la texture per copia o lasciarla per reference
    public ArrayList<DisplayableObject> getDisplayable(){
        ArrayList<DisplayableObject> arrDisp = new ArrayList<>();
        for(Bullet b : bulletSet)
            arrDisp.add(b.getDisplayableObject());
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

    // questa funzione deve controllare e rimuovere proiettili che collidono tra di loro
    private void checkMutualCollisions(){
        HashSet<Bullet> tmp = new HashSet<>();
        for(Bullet b : bulletSet){
            for(Bullet bu : bulletSet){
                if(b.isHitting(bu.getBody(), bu.getSource())){
                    tmp.add(b);
                    tmp.add(bu);
                }
            }
        }
        bulletSet.removeAll(tmp);
    }
}

package com.mygdx.entityManagement;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entities.Bullet;
import com.mygdx.Commons;
import com.mygdx.entities.TypeOfEntity;
import java.util.*;

public class BulletManager implements Manager{
    private final Set<Bullet> bulletSet = new HashSet<>();

    public void addBullet(int startX, int startY, int vel, TypeOfEntity source) {
        bulletSet.add(new Bullet(startX, startY, vel, source));
    }

    @Override
    public void updateEntities(){
        for(Bullet b : bulletSet)
            b.updatePosition();
        this.checkBorders();
        this.checkMutualCollisions();
    }

    @Override
    public ArrayList<AbstractMap.SimpleEntry<Float, Float>> getPosition(){
        ArrayList<AbstractMap.SimpleEntry<Float, Float>> arrDisp = new ArrayList<>();
        for(Bullet b : bulletSet)
            arrDisp.add(new AbstractMap.SimpleEntry<>(b.getX(), b.getY()));
        return arrDisp;
    }

    public int getBulletCollision(Rectangle body, TypeOfEntity type){
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
            if(b.getY() > Commons.WORLD_Y_END || b.getY() < Commons.WORLD_Y_START) {
                iter.remove();
            }
        }
    }

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

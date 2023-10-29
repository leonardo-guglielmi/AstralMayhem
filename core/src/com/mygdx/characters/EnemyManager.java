package com.mygdx.characters;

import com.mygdx.bullets.BulletManager;
import com.mygdx.displayable.DisplayObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class EnemyManager {
    HashSet<Enemy> enemySet = new HashSet<>();
    BulletManager bm;

    public EnemyManager(BulletManager bm){
        this.bm = bm;
    }

    public void addEnemy(Enemy e){
        enemySet.add(e);
    }

    public ArrayList<DisplayObject> getDisplayable(){
        ArrayList<DisplayObject> arrDisp = new ArrayList<>();
        for(Enemy e : enemySet)
            arrDisp.add(e.getDisplayObject());
        return arrDisp;
    }

    public void update(){
        for(Enemy e : enemySet)
            e.move();
        Iterator<Enemy> iter = enemySet.iterator();
        while(iter.hasNext()){
            Enemy e = iter.next();
            e.move();
            if(e.checkCollision())
                iter.remove();
        }
    }
}

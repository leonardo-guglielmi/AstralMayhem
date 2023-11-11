package com.mygdx.manager;

import com.mygdx.characters.Enemy;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.enemyStuff.EnemyFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class EnemyManager implements Manager{
    private final HashSet<Enemy> enemySet = new HashSet<>();
    private final BulletManager bm;

    private final EnemyFactory ef;

    public EnemyManager(BulletManager bm){
        this.bm = bm;
        ef = new EnemyFactory(bm);
    }

    public void addEnemy(){
        enemySet.add(ef.createBaseEnemy());
    }

    @Override
    public ArrayList<DisplayableObject> getDisplayable(){
        ArrayList<DisplayableObject> arrDisp = new ArrayList<>();
        for(Enemy e : enemySet)
            arrDisp.add(e.getDisplayableObject());
        return arrDisp;
    }

    @Override
    public void update(){
        Iterator<Enemy> iter = enemySet.iterator();
        while(iter.hasNext()){
            Enemy e = iter.next();
            if(e.getNumCollisions() >= 1)
                iter.remove();
            else{
                e.exec();
            }
        }
    }
}

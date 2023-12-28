package com.mygdx.entityManagement;

import com.mygdx.Commons;
import com.mygdx.entities.Enemy;
import com.mygdx.entities.Hero;
import com.mygdx.observers.Observable;
import com.mygdx.observers.Observed;
import com.mygdx.observers.Observer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class EnemyManager implements Manager, Observable {
    private final HashSet<Enemy> enemySet = new HashSet<>();
    private final BulletManager bm;
    private final EnemyFactory ef;
    private Observed obs = new Observed();
    private boolean GAMEOVER_LIMIT_REACHED = false;

    public EnemyManager(BulletManager bm, Hero h){
        this.bm = bm;
        ef = new EnemyFactory(bm, h);
    }

    public void addBaseEnemy(){
        enemySet.add(ef.createBaseEnemy());
    }

    public void addAdvancedEnemy(){
        enemySet.add(ef.createAdvanceEnemy());
    }

    @Override
    public ArrayList<AbstractMap.SimpleEntry<Float, Float>> getPosition(){
        ArrayList<AbstractMap.SimpleEntry<Float, Float>> arrDisp = new ArrayList<>();
        for(Enemy e : enemySet)
            arrDisp.add(new AbstractMap.SimpleEntry<>(e.getX(), e.getY()));
        return arrDisp;
    }

    @Override
    public void updateEntities(){
        Iterator<Enemy> iter = enemySet.iterator();
        while(iter.hasNext()){
            Enemy e = iter.next();

            if(e.getNumCollisions() >= 1)
                iter.remove();
            else{
                e.update();
                if(e.getY() <= Commons.GAMEOVER_LIMIT) {
                    GAMEOVER_LIMIT_REACHED = true;
                    obs.notifyObservers();
                }
            }
        }
    }

    @Override
    public void addObserver(Observer o){
        obs.addObserver(o);
    }

    public boolean checkGameoverLimit(){
        return GAMEOVER_LIMIT_REACHED;
    }
}

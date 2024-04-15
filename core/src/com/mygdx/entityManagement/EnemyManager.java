package com.mygdx.entityManagement;


import com.mygdx.enemyLogic.AdvancedEnemyStrategy;
import com.mygdx.enemyLogic.BaseEnemyStrategy;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.utils.Commons;
import com.mygdx.entities.Enemy;
import com.mygdx.entities.Hero;
import com.mygdx.observers.Observable;
import com.mygdx.observers.Subject;
import com.mygdx.observers.Observer;
import com.mygdx.utils.Pair;
import com.mygdx.utils.Timer;
import com.mygdx.utils.Triplet;

import java.util.*;

public class EnemyManager implements Manager, Observable {
    private final Set<Enemy> enemySet = new HashSet<>();
    private final Timer baseEnemySpawnTimer = new Timer(2);
    private final Timer advanceEnemySpawnTimer = new Timer(3);
    private final BulletManager bm;
    private final Hero h;
    private final Subject obs = new Subject();
    private boolean GAMEOVER_LIMIT_REACHED = false;

    public EnemyManager(BulletManager bm, Hero h){
        this.bm = bm;
        this.h = h;
    }

    @Override
    public ArrayList<Pair<Float, Float>> getPrintInfo(){
        ArrayList< Pair<Float, Float> > arrDisp = new ArrayList<>();
        for(Enemy e : enemySet)
            arrDisp.add(new Triplet<>( e.getX(), e.getY(), e.getTypeOfEnemy() ));
        return arrDisp;
    }

    @Override
    public void updateEntities(){
        if(baseEnemySpawnTimer.check()) {
            Random coordinateGenerator = new Random();
            int startingX = Commons.ENEMY_SPAWN_X[coordinateGenerator.nextInt(Commons.ENEMY_SPAWN_X.length)];
            int startingY = Commons.ENEMY_SPAWN_Y[coordinateGenerator.nextInt(Commons.ENEMY_SPAWN_Y.length)];

            Enemy e = new Enemy(startingX, startingY, bm);
            e.setStrategy(new BaseEnemyStrategy(e, 3));
            enemySet.add(e);
        }
        if(advanceEnemySpawnTimer.check()){
            Enemy e = new Enemy(Commons.WORLD_X_START, 500, bm);
            e.setStrategy(new AdvancedEnemyStrategy(e, h, 3));
            enemySet.add(e);
        }

        Iterator<Enemy> iter = enemySet.iterator();
        while(iter.hasNext()){
            Enemy e = iter.next();

            if(e.getNumCollisions() >= 1) {
                GameScreen.updateScore(100);
                iter.remove();
            }
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

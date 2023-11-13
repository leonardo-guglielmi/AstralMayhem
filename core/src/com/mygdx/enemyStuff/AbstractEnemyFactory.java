package com.mygdx.enemyStuff;

import com.mygdx.characters.Enemy;

public interface AbstractEnemyFactory {

    Enemy createBaseEnemy();

    Enemy createAdvanceEnemy();
}

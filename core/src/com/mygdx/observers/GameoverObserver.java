package com.mygdx.observers;

import com.mygdx.AstralMayhem;
import com.mygdx.entities.Earth;
import com.mygdx.entities.Hero;
import com.mygdx.entityManagement.EnemyManager;
import com.mygdx.gameStates.GameoverScreen;

public class GameoverObserver implements Observer{
    AstralMayhem game;
    Hero h;
    Earth earth;
    EnemyManager em;

    public GameoverObserver(AstralMayhem game, Hero h, Earth earth, EnemyManager em){
        this.game = game;
        this.h = h;
        this.earth = earth;
        this.em = em;
    }

    @Override
    public void observe(){
        String str = "";
        if(h.getHp() <= 0){
            str += "Player dead\n";
        }
        if(earth.getHp() <= 0){
            str += "Earth dead\n";
        }
        if(em.checkGameoverLimit()){
                str += "Aliens reach earth\n";
        }
        if(!str.isEmpty())
            game.setScreen(new GameoverScreen(game, str));
    }
}

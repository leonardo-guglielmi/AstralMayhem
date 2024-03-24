package com.mygdx.observers;

import com.mygdx.AstralMayhem;
import com.mygdx.databaseConnection.ResultDAO;
import com.mygdx.databaseConnection.ConcreteResultDAO;
import com.mygdx.databaseConnection.Result;
import com.mygdx.entities.Earth;
import com.mygdx.entities.Hero;
import com.mygdx.entityManagement.EnemyManager;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.gameStates.GameoverScreen;
import com.mygdx.utils.Commons;

import java.sql.SQLException;

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

        game.getScreen().dispose();
        game.setScreen(new GameoverScreen(game, str, GameScreen.getScore()));
    }
}

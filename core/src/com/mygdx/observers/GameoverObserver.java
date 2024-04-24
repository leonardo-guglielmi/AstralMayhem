package com.mygdx.observers;

import com.mygdx.AstralMayhem;
import com.mygdx.entities.Earth;
import com.mygdx.entities.Hero;
import com.mygdx.entityManagement.EnemyManager;
import com.mygdx.gameStates.GameScreen;
import com.mygdx.gameStates.GameoverScreen;

public class GameoverObserver implements Observer{
    private final AstralMayhem game;
    private final Hero h;
    private final Earth earth;
    private final EnemyManager em;

    private final GameScreen gs;



    public GameoverObserver(AstralMayhem game, Hero h, Earth earth, EnemyManager em, GameScreen gs){
        this.game = game;
        this.h = h;
        this.earth = earth;
        this.em = em;
        this.gs = gs;
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

        if(game.getScreen() != null)
            game.getScreen().dispose();
        game.setScreen(new GameoverScreen(game, str, gs.getScore(), gs.getTime()));
    }
}

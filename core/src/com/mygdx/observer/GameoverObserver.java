package com.mygdx.observer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.AstralMayhem;
import com.mygdx.Earth;
import com.mygdx.characters.Hero;
import com.mygdx.entityManagement.EnemyManager;
import com.mygdx.gameStates.GameoverScreen;

public class GameoverObserver implements Observer{
    AstralMayhem game;
    OrthographicCamera camera;
    Hero h;
    Earth earth;
    EnemyManager em;

    public GameoverObserver(AstralMayhem game, OrthographicCamera camera, Hero h, Earth earth, EnemyManager em){
        this.game = game;
        this.camera = camera;
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
                str += "Aliens reach earth";
        }
        if(!str.isEmpty())
            game.setScreen(new GameoverScreen(game, str, camera));
    }
}

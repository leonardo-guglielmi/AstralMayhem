package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.AstralMayhem;
import com.mygdx.Commons;
import com.mygdx.entities.Earth;
import com.mygdx.Timer;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.entityManagement.EnemyManager;
import com.mygdx.entities.Hero;
import com.mygdx.observers.GameoverObserver;

import java.util.AbstractMap;
import java.util.ArrayList;

public class GameScreen implements Screen {
    private final AstralMayhem game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final BulletManager bm = new BulletManager();
    private final Hero hero;
    private final Earth earth;
    private EnemyManager em;
    private final BitmapFont textPrinter = new BitmapFont();
    private Timer baseEnemyTimer = new Timer(2);
    private Timer advanceEnemyTimer = new Timer(3);
    private GameoverObserver go;

    public GameScreen(final AstralMayhem game){
        this.game = game;
        camera.setToOrtho(false);
        game.batch.setProjectionMatrix(camera.combined);

        if(!game.am.contains("entities/hero.png"))
            game.am.load("entities/hero.png", Texture.class);
        if(!game.am.contains("entities/earth.png"))
            game.am.load("entities/earth.png", Texture.class);
        if(!game.am.contains("entities/enemy.png"))
            game.am.load("entities/enemy.png", Texture.class);
        if(!game.am.contains("entities/advanced_enemy.png"))
            game.am.load("entities/advanced_enemy.png", Texture.class);
        if(!game.am.contains("entities/bullet.png"))
            game.am.load("entities/bullet.png", Texture.class);
        game.am.finishLoading();

        hero = new Hero(game.am,50, 100, bm);
        earth = new Earth(game.am, bm, Commons.WORLD_X_START, Commons.WORLD_Y_START);
        em = new EnemyManager(bm, hero);

        go = new GameoverObserver(game, hero, earth, em);
        hero.addObserver(go);
        earth.addObserver(go);
        em.addObserver(go);
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        updateLogic();
        printGame();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }

    private void updateLogic(){
        // updating hero logic
        hero.update();

        //updating managers
        bm.updateEntities();
        em.updateEntities();

        //updating earth
        earth.update();

        //spawning enemies
        if(baseEnemyTimer.check())
            em.addBaseEnemy();
        if(advanceEnemyTimer.check())
            em.addAdvancedEnemy();
    }

    private void printGame(){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        textPrinter.setColor(Color.WHITE);
        game.batch.begin();
        {
            game.batch.draw(game.am.<Texture>get("entities/earth.png"), earth.getX(), earth.getY());
            game.batch.draw(game.am.<Texture>get("entities/hero.png"), hero.getX(), hero.getY());

            ArrayList<AbstractMap.SimpleEntry<Float, Float>> bulletsDisp = bm.getPosition();
            for(AbstractMap.SimpleEntry<Float, Float> b : bulletsDisp)
                game.batch.draw(game.am.<Texture>get("entities/bullet.png"), b.getKey(), b.getValue());

            ArrayList<AbstractMap.SimpleEntry<Float, Float>> enemyDisp = em.getPosition();
            for(AbstractMap.SimpleEntry<Float, Float> e : enemyDisp)
                game.batch.draw(game.am.<Texture>get("entities/enemy.png"), e.getKey(), e.getValue());

            textPrinter.draw(game.batch, "player_hp: " + hero.getHp(), 500, 500);
            textPrinter.draw(game.batch, "earth_hp: " + earth.getHp(), 500, 500-32);
        }
        game.batch.end();
    }


}


package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
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
        // setting-up game environment
        this.game = game;
        camera.setToOrtho(false);
        game.batch.setProjectionMatrix(camera.combined);
        loadTextures();

        // setting-up game logic elements
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
        printBackGrounds();
        printEntities();
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

    private void loadTextures(){
        // loading background texture
        if(!game.am.contains(Commons.GAME_BACKGROUND_IMG_PATH))
            game.am.load(Commons.GAME_BACKGROUND_IMG_PATH, Texture.class);
        if(!game.am.contains(Commons.UI_BACKGROUND_IMG_PATH))
            game.am.load(Commons.UI_BACKGROUND_IMG_PATH, Texture.class);
        if(!game.am.contains(Commons.HEART_IMG_PATH))
            game.am.load(Commons.HEART_IMG_PATH, Texture.class);

        // loading entities texture
        if(!game.am.contains(Commons.HERO_IMG_PATH))
            game.am.load(Commons.HERO_IMG_PATH, Texture.class);
        if(!game.am.contains(Commons.EARTH_IMG_PATH))
            game.am.load(Commons.EARTH_IMG_PATH, Texture.class);
        if(!game.am.contains(Commons.ENEMY_IMG_PATH))
            game.am.load(Commons.ENEMY_IMG_PATH, Texture.class);
        if(!game.am.contains(Commons.ADVANCED_ENEMY_IMG_PATH))
            game.am.load(Commons.ADVANCED_ENEMY_IMG_PATH, Texture.class);
        if(!game.am.contains(Commons.BULLET_IMG_PATH))
            game.am.load(Commons.BULLET_IMG_PATH, Texture.class);

        game.am.finishLoading();
    }

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

    private void printBackGrounds(){
        ScreenUtils.clear(1,1,1,0);
        camera.update();
        textPrinter.setColor(Color.WHITE);

        game.batch.begin();
        {
            game.batch.draw(game.am.<Texture>get(Commons.GAME_BACKGROUND_IMG_PATH), Commons.WORLD_X_START, Commons.WORLD_Y_START);
            game.batch.draw(game.am.<Texture>get(Commons.UI_BACKGROUND_IMG_PATH),
                    Commons.WORLD_X_START +game.am.<Texture>get(Commons.GAME_BACKGROUND_IMG_PATH).getWidth(),
                    Commons.WORLD_Y_START);

            textPrinter.draw(game.batch, "HP", Commons.WORLD_X_END+67, Commons.WORLD_Y_END-190);
            for(int i=0; i < hero.getHp(); i++)
                game.batch.draw(game.am.<Texture>get(Commons.HEART_IMG_PATH), Commons.WORLD_X_END+60, Commons.WORLD_Y_END-250-i*32);

        }
        game.batch.end();
    }

    private void printEntities(){
        game.batch.begin();
        {
            game.batch.draw(game.am.<Texture>get(Commons.EARTH_IMG_PATH), earth.getX(), earth.getY());
            game.batch.draw(game.am.<Texture>get(Commons.HERO_IMG_PATH), hero.getX(), hero.getY());

            ArrayList<AbstractMap.SimpleEntry<Float, Float>> bulletsDisp = bm.getPosition();
            for(AbstractMap.SimpleEntry<Float, Float> b : bulletsDisp)
                game.batch.draw(game.am.<Texture>get(Commons.BULLET_IMG_PATH), b.getKey(), b.getValue());

            ArrayList<AbstractMap.SimpleEntry<Float, Float>> enemyDisp = em.getPosition();
            for(AbstractMap.SimpleEntry<Float, Float> e : enemyDisp)
                game.batch.draw(game.am.<Texture>get(Commons.ENEMY_IMG_PATH), e.getKey(), e.getValue());

            textPrinter.draw(game.batch, "earth_hp: " + earth.getHp(), 500, 500-32);
        }
        game.batch.end();
    }


}


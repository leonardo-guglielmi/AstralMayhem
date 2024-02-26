package com.mygdx.gameStates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.AstralMayhem;
import com.mygdx.enemyLogic.AdvancedEnemyStrategy;
import com.mygdx.enemyLogic.BaseEnemyStrategy;
import com.mygdx.utils.Commons;
import com.mygdx.entities.Earth;
import com.mygdx.utils.Timer;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.entityManagement.EnemyManager;
import com.mygdx.entities.Hero;
import com.mygdx.observers.GameoverObserver;
import com.mygdx.utils.Pair;
import com.mygdx.utils.Triplet;
import java.util.ArrayList;

public class GameScreen implements Screen {
    private final AstralMayhem game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final BulletManager bm;
    private final Hero hero;
    private final Earth earth;
    private final EnemyManager em;

    // todo: spostare questi due nella parte di enemy manager
    private final Timer baseEnemyTimer = new Timer(2);
    private final Timer advanceEnemyTimer = new Timer(3);
    public static long score;
    public float time = 0;

    public GameScreen(final AstralMayhem game){
        // setting-up game environment and graphics
        score = 0;
        this.game = game;
        camera.setToOrtho(false);
        game.batch.setProjectionMatrix(camera.combined);
        loadTextures();

        // setting-up game logic elements
        bm = new BulletManager(game.am);
        hero = new Hero(game.am,50, 100, bm); //todo: aggiusta le coordinate di spawn
        earth = new Earth(game.am, bm, Commons.WORLD_X_START, Commons.WORLD_Y_START);
        em = new EnemyManager(bm, hero);

        GameoverObserver go = new GameoverObserver(game, hero, earth, em);
        hero.addObserver(go);
        earth.addObserver(go);
        em.addObserver(go);

    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        time += delta;
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
        if(!game.am.contains(Commons.EARTH_HP_BAR))
            game.am.load(Commons.EARTH_HP_BAR, Texture.class);

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
        // setting-up teh environment to print
        ScreenUtils.clear(1,1,1,0);
        camera.update();
        game.textPrinter.setColor(Color.WHITE);

        game.batch.begin();
        {
            // print all backgrounds
            game.batch.draw(game.am.<Texture>get(Commons.GAME_BACKGROUND_IMG_PATH), Commons.WORLD_X_START, Commons.WORLD_Y_START);
            game.batch.draw(game.am.<Texture>get(Commons.UI_BACKGROUND_IMG_PATH),
                    Commons.WORLD_X_START +game.am.<Texture>get(Commons.GAME_BACKGROUND_IMG_PATH).getWidth(),
                    Commons.WORLD_Y_START);

            // print all game info
            game.textPrinter.draw(game.batch, "TIME: "+(int)time, Commons.WORLD_X_END+67, Commons.WORLD_Y_END-130);
            game.textPrinter.draw(game.batch, "SCORE: "+score, Commons.WORLD_X_END+67, Commons.WORLD_Y_END-150);

            game.textPrinter.draw(game.batch, "HP", Commons.WORLD_X_END+67, Commons.WORLD_Y_END-190);
            for(int i=0; i < hero.getHp(); i++)
                game.batch.draw(game.am.<Texture>get(Commons.HEART_IMG_PATH), Commons.WORLD_X_END+60, Commons.WORLD_Y_END-250-i*32);

            game.textPrinter.draw(game.batch, "EARTH HP", Commons.WORLD_X_END+158, Commons.WORLD_Y_END-190);
            game.batch.draw(game.am.<Texture>get(Commons.EARTH_HP_BAR), Commons.WORLD_X_END+170, Commons.WORLD_Y_END-620, 40, 4*earth.getHp());
        }
        game.batch.end();

    }

    private void printEntities(){
        game.batch.begin();
        {
            // print hero and earth texture
            game.batch.draw(game.am.<Texture>get(Commons.EARTH_IMG_PATH), earth.getX(), earth.getY());
            game.batch.draw(game.am.<Texture>get(Commons.HERO_IMG_PATH), hero.getX(), hero.getY());

            // print bullets
            ArrayList< Pair<Float, Float> > bulletDisp = bm.getPrintInfo();
            for(Pair<Float, Float> p : bulletDisp)
                game.batch.draw(game.am.<Texture>get(Commons.BULLET_IMG_PATH), p.x, p.y);

            // print enemies
            ArrayList< Pair<Float, Float> > enemyDisp = em.getPrintInfo();
            for(Pair<Float, Float> e : enemyDisp) {
                Triplet<Float, Float, Class<?>> t = (Triplet<Float, Float, Class<?>>)e;
                if(t.z == BaseEnemyStrategy.class)
                    game.batch.draw(game.am.<Texture>get(Commons.ENEMY_IMG_PATH), t.x, t.y);
                else if(t.z == AdvancedEnemyStrategy.class)
                    game.batch.draw(game.am.<Texture>get(Commons.ADVANCED_ENEMY_IMG_PATH), t.x, t.y);
            }
        }
        game.batch.end();
    }


}


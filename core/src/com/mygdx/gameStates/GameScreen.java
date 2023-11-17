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
import com.mygdx.Earth;
import com.mygdx.Timer;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.entityManagement.EnemyManager;
import com.mygdx.characters.Hero;
import com.mygdx.displayable.DisplayableObject;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final AstralMayhem game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final BulletManager bm = new BulletManager();
    private final Hero hero = new Hero(new Texture(Gdx.files.internal("heroo.png")), 50, 100, bm);
    private final Earth earth = new Earth(new Texture(Gdx.files.internal("earth.png")), bm, Commons.HORIZONTAL_START, Commons.VERTICAL_START);
    private final EnemyManager em = new EnemyManager(bm, hero);
    private final BitmapFont textPrinter = new BitmapFont();
    private Timer baseEnemyTimer = new Timer(2);
    private Timer advanceEnemyTimer = new Timer(3);

    public GameScreen(final AstralMayhem game){
        this.game = game;
        camera.setToOrtho(false);
        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateLogic();
        printGame();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        hero.disposeTexture();
    }

    private void updateLogic(){
        // updating hero logic
        hero.handleInput();
        hero.getNumCollisions();

        //updating managers
        bm.update();
        em.update();

        //updating earth
        updateLogic();
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
            DisplayableObject dispEarth = earth.getDisplayableObject();
            game.batch.draw(dispEarth.tx, dispEarth.posX, dispEarth.posY);

            DisplayableObject dispHero = hero.getDisplayableObject();
            game.batch.draw(dispHero.tx, dispHero.posX, dispHero.posY);

            ArrayList<DisplayableObject> bulletsDisp = bm.getDisplayable();
            for(DisplayableObject b : bulletsDisp)
                game.batch.draw(b.tx, b.posX, b.posY);

            ArrayList<DisplayableObject> enemyDisp = em.getDisplayable();
            for(DisplayableObject e : enemyDisp)
                game.batch.draw(e.tx, e.posX, e.posY);

            textPrinter.draw(game.batch, "player_hp: " + hero.getHp(), (float) Commons.V_WIDTH /2, (float) Commons.V_HEIGHT /2);
            textPrinter.draw(game.batch, "earth_hp: " + earth.getHp(), (float) Commons.V_WIDTH /2, (float) Commons.V_HEIGHT /2-32);
        }
        game.batch.end();
    }


}


package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.AstralMayhem;
import com.mygdx.manager.BulletManager;
import com.mygdx.manager.EnemyManager;
import com.mygdx.characters.Hero;
import com.mygdx.displayable.DisplayableObject;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final AstralMayhem game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final BulletManager bm = new BulletManager();
    private final EnemyManager em = new EnemyManager(bm);
    private final Hero hero = new Hero(new Texture(Gdx.files.internal("heroo.png")), 0, 100, bm);
    private final BitmapFont textPrinter = new BitmapFont();
    float time = 0;

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
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        hero.handleInput();
        hero.checkCollision();
        bm.update();
        em.update();
        time += delta;
        if(time > 2){
            em.addEnemy();
            time = 0;
        }

        textPrinter.setColor(Color.WHITE);
        game.batch.begin();
        {
            DisplayableObject dispHero = hero.getDisplayableObject();
            game.batch.draw(dispHero.tx, dispHero.posX, dispHero.posY);

            ArrayList<DisplayableObject> bulletsDisp = bm.getDisplayable();
            for(DisplayableObject b : bulletsDisp)
                game.batch.draw(b.tx, b.posX, b.posY);

            ArrayList<DisplayableObject> enemyDisp = em.getDisplayable();
            for(DisplayableObject e : enemyDisp)
                game.batch.draw(e.tx, e.posX, e.posY);

            textPrinter.draw(game.batch, "player_hp: " + hero.getHp(), 500, 350);
        }
        game.batch.end();
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

    }
}

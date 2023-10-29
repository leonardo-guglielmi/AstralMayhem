package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.AstralMayhem;
import com.mygdx.bullets.BulletManager;
import com.mygdx.characters.Hero;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private final AstralMayhem game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final BulletManager bm = new BulletManager();
    private final Hero hero = new Hero(new Texture(Gdx.files.internal("hero.png")), 0, 100, bm);

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
        bm.update(delta);

        game.batch.begin();
        {
            game.batch.draw(hero.getTexture(), hero.getPosX(), hero.getPosY());
            ArrayList<Texture> bulletsTexture = bm.getBulletsTexture();
            //for(Texture tx : bulletsTexture)
                // game.batch.draw(); todo: come faccio ad ottenere anche la posizione?
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

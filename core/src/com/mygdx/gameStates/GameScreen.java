package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.AstralMayhem;
import com.mygdx.Hero;

public class GameScreen implements Screen {

    private final AstralMayhem game;
    private final Hero hero = new Hero(new Texture(Gdx.files.internal("hero.png")), 0, 0);
    private OrthographicCamera camera = new OrthographicCamera();

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

        hero.input.handle();

        game.batch.begin();
        {
            game.batch.draw(hero.getTexture(), hero.getPosX(), hero.getPosY());
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

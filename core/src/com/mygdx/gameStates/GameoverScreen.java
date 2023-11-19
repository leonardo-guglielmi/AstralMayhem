package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.AstralMayhem;
import com.mygdx.Commons;

public class GameoverScreen implements Screen {

    private AstralMayhem game;
    private String gameoverText;
    private final BitmapFont textPrinter = new BitmapFont();
    private final OrthographicCamera camera;

    public GameoverScreen(AstralMayhem game, String gameoverText, OrthographicCamera camera){
        this.game = game;
        this.gameoverText = gameoverText;
        this.camera = camera;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        textPrinter.setColor(Color.BLACK);
        game.batch.begin();
        {
            textPrinter.draw(game.batch, gameoverText, 500, 500);
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

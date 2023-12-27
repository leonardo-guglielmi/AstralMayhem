package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jogamp.opengl.util.texture.spi.LEDataInputStream;
import com.mygdx.AstralMayhem;
import com.mygdx.Commons;

import java.util.ArrayList;
import java.util.List;

public class GameoverScreen implements Screen {

    private AstralMayhem game;
    private String gameoverText;
    private final Texture titleTx = new Texture(Gdx.files.internal("gameover/gameOverTitle.png"));
    private final Texture skullTx = new Texture(Gdx.files.internal("gameover/skull.png"));

    public GameoverScreen(AstralMayhem game, String gameoverText){
        this.game = game;
        this.gameoverText = gameoverText;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.textPrinter.setColor(Color.BLACK);

        game.batch.begin();
        {
            game.batch.draw(titleTx, (float)Commons.WINDOW_WIDTH /2 -(float)titleTx.getWidth()/2, Commons.WINDOW_HEIGHT -50 -(float)titleTx.getHeight()/2);
            game.batch.draw(skullTx, (float)Commons.WINDOW_WIDTH /2 -(float)skullTx.getWidth()/2, Commons.WINDOW_HEIGHT -350 -(float)skullTx.getHeight()/2);
            game.textPrinter.draw(game.batch, gameoverText, (float)Commons.WINDOW_WIDTH/2 -500, Commons.WINDOW_HEIGHT -700);
            game.textPrinter.draw(game.batch, "click everywhere to go back to menu", 960 - 110, 1080 - 800);
        }
        game.batch.end();

        if(Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            this.dispose();
        }
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
        titleTx.dispose();
        skullTx.dispose();
    }
}

package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.AstralMayhem;
import com.mygdx.databaseConnection.ConcreteResultDAO;
import com.mygdx.databaseConnection.Result;
import com.mygdx.databaseConnection.ResultDAO;
import com.mygdx.inputManagement.InputHandler;
import com.mygdx.inputManagement.KeyboardGameInputHandler;
import com.mygdx.utils.Commons;

import java.sql.SQLException;
import java.util.List;

public class MenuScreen implements Screen {

    private final AstralMayhem game;
    private List<Result> results;

    public MenuScreen(AstralMayhem game){
        // loading gameover info
        this.game = game;
        this.results = results;

        try {
            ResultDAO resultDAO = new ConcreteResultDAO();
            List<Result> bestResults = resultDAO.get();
            this.results = bestResults;
        }catch (SQLException e){
            System.out.println("Errore nel caricamento dei dati");
        }



        // loading graphic elements


        if(!game.am.contains("menu/invaders.png"))
            game.am.load("menu/invaders.png", Texture.class);
        game.am.finishLoading();

        // sending score results to the server (if logged in)
        /*
        game.client.sendScore(Score)
         */
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        // setup to display all graphic elements
        ScreenUtils.clear(0,0,0,1);
        game.textPrinter.setColor(Color.WHITE);

        // draw everything
        game.batch.begin();
        {

            game.textPrinter.draw(game.batch, "Press C to close the game", Commons.WORLD_X_END-110, Commons.WORLD_Y_END-50);
            game.textPrinter.draw(game.batch, "Press S to start a new game ", Commons.WORLD_X_END-110, Commons.WORLD_Y_END-70);
            game.textPrinter.draw(game.batch, "BEST 5 GAMES: ", Commons.WORLD_X_END-110, Commons.WORLD_Y_END-130);
            game.textPrinter.draw(game.batch, "---------------------------------------------------------", Commons.WORLD_X_END-110, Commons.WORLD_Y_END-150);

            int i = 20;
            for (Result r  : results) {
                game.textPrinter.draw(game.batch, "PLAYER:"+r.getPlayer() + "| SCORE:"+r.getPoints() + "|TIME:" + r.getTime(), Commons.WORLD_X_END-110, Commons.WORLD_Y_END-150-i);
                i+=20;
            }

            game.batch.draw(game.am.<Texture>get(Commons.MENU_INVADERS_IMG_PATH),
                    (float)Commons.WINDOW_WIDTH /2 -(float)game.am.<Texture>get(Commons.MENU_INVADERS_IMG_PATH).getWidth()/2,
                    Commons.WINDOW_HEIGHT -350 - (float) game.am.<Texture>get(Commons.MENU_INVADERS_IMG_PATH).getHeight() /2);

        }
        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            game.setScreen(new GameScreen(game));
            this.dispose();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            game.setScreen(new GameScreen(game));
            this.dispose();
            Gdx.app.exit();
        }
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
}

package com.mygdx.gameStates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.AstralMayhem;
import com.mygdx.databaseConnection.ConcreteResultModelDAO;
import com.mygdx.databaseConnection.ResultModel;
import com.mygdx.databaseConnection.ResultModelDAO;
import com.mygdx.inputManagement.InputHandler;
import com.mygdx.inputManagement.menuManagement.KeyboardMenuInputHandler;
import com.mygdx.utils.Commons;
import com.mygdx.utils.TextInputProcessor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen implements Screen {

    private final AstralMayhem game;
    private List<ResultModel> bestScores = new ArrayList<>();
    private List<ResultModel> bestUserScores = new ArrayList<>();
    private final TextInputProcessor tip = new TextInputProcessor();
    private final InputHandler input;
    private final ResultModelDAO resultModelDAO = new ConcreteResultModelDAO();

    public MenuScreen(AstralMayhem game){
        // loading gameover info
        this.game = game;
        input = new KeyboardMenuInputHandler(game, this,tip);
        loadGraphics();
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

            game.textPrinter.draw(game.batch, "> Press Q to close the game", Commons.WORLD_X_END-90, Commons.WORLD_Y_END-250);
            game.textPrinter.draw(game.batch, "> Press S to start a new game ", Commons.WORLD_X_END-90, Commons.WORLD_Y_END-275);
            game.textPrinter.draw(game.batch, "> Press T to type username (max 20 chars):",Commons.WORLD_X_END-90, Commons.WORLD_Y_END-310 );
            game.textPrinter.draw(game.batch, game.username, Commons.WORLD_X_END-80, Commons.WORLD_Y_END-330);
            for(int i=0; i<30; i++)
                game.textPrinter.draw(game.batch, "_", Commons.WORLD_X_END-80+i*8, Commons.WORLD_Y_END-332);


            game.textPrinter.draw(game.batch, "> Press B to get the best scores", Commons.WORLD_X_END-90, Commons.WORLD_Y_END-400);
            if(bestScores != null) {
                for (int i = 0; i < bestScores.size(); i++)
                    game.textPrinter.draw(game.batch, bestScores.get(i).toString(), Commons.WORLD_X_END-90, Commons.WORLD_Y_END-420-i*20);
            }
            else {
               game.textPrinter.draw(game.batch, "ERROR CONNECTING TO DATABASE", Commons.WORLD_X_END-90, Commons.WORLD_Y_END-420);
            }

            game.textPrinter.draw(game.batch, "> Press Y to get your best scores", Commons.WORLD_X_END-90, Commons.WORLD_Y_END-520);
            if(bestUserScores != null) {
                for (int i = 0; i < bestUserScores.size(); i++)
                    game.textPrinter.draw(game.batch, bestUserScores.get(i).toString(), Commons.WORLD_X_END - 90, Commons.WORLD_Y_END - 540 - i * 20);
            }
            else {
                game.textPrinter.draw(game.batch, "ERROR CONNECTING TO DATABASE", Commons.WORLD_X_END-90, Commons.WORLD_Y_END-540);
            }


            game.batch.draw(game.am.<Texture>get(Commons.MENU_TITLE_IMG_PNG),
                    (float)Commons.WINDOW_WIDTH /2 -(float)game.am.<Texture>get(Commons.MENU_TITLE_IMG_PNG).getWidth()/2-200,
                    Commons.WINDOW_HEIGHT -50 - (float) game.am.<Texture>get(Commons.MENU_TITLE_IMG_PNG).getHeight() /2);
            game.batch.draw(game.am.<Texture>get(Commons.MENU_INVADERS_IMG_PATH),
                    (float)Commons.WINDOW_WIDTH /2 -(float)game.am.<Texture>get(Commons.MENU_INVADERS_IMG_PATH).getWidth()/2-200,
                    Commons.WINDOW_HEIGHT -450 - (float) game.am.<Texture>get(Commons.MENU_INVADERS_IMG_PATH).getHeight() /2);

        }
        game.batch.end();
        
        input.handle();
        if(tip.isReading())
            game.username = tip.getText();
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

    private void loadGraphics(){
        if(!game.am.contains(Commons.MENU_INVADERS_IMG_PATH))
            game.am.load(Commons.MENU_INVADERS_IMG_PATH, Texture.class);
        if(!game.am.contains(Commons.MENU_TITLE_IMG_PNG))
            game.am.load(Commons.MENU_TITLE_IMG_PNG, Texture.class);
        game.am.finishLoading();
    }

    public void loadBestScore(){
        try {
            bestScores = resultModelDAO.getBest();
        }
        catch (SQLException e){
            bestScores = null;
        }
    }

    public void loadUserScore(){
        try {
            bestUserScores = resultModelDAO.getByUSer(game.username);
        }
        catch (SQLException e){
            bestUserScores = null;
        }
    }

}

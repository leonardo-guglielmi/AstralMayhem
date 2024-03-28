package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.AstralMayhem;
import com.mygdx.databaseConnection.ConcreteResultModelDAO;
import com.mygdx.databaseConnection.ResultModel;
import com.mygdx.databaseConnection.ResultModelDAO;
import com.mygdx.utils.Commons;

import java.sql.SQLException;

public class GameoverScreen implements Screen {

    private final AstralMayhem game;
    private final String gameoverText;
    private final int score;

    private float time = 0;

    public GameoverScreen(AstralMayhem game, String gameoverText, int score){
        // loading gameover info
        this.game = game;
        this.gameoverText = gameoverText;
        this.score = score;

        // loading graphic elements
        if(!game.am.contains("gameover/gameOverTitle.png"))
            game.am.load("gameover/gameOverTitle.png", Texture.class);
        if(!game.am.contains("gameover/skull.png"))
            game.am.load("gameover/skull.png", Texture.class);
        game.am.finishLoading();

    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        // setup to display all graphic elements
        ScreenUtils.clear(204/255f,0,0,1);
        game.textPrinter.setColor(Color.WHITE);

        // draw everything
        game.batch.begin();
        {
            game.batch.draw(game.am.<Texture>get(Commons.GAMEOVER_TITLE_IMG_PATH),
                    (float)Commons.WINDOW_WIDTH /2 -(float)game.am.<Texture>get(Commons.GAMEOVER_TITLE_IMG_PATH).getWidth()/2,
                    Commons.WINDOW_HEIGHT -50 -(float)game.am.<Texture>get(Commons.GAMEOVER_TITLE_IMG_PATH).getHeight()/2);

            game.batch.draw(game.am.<Texture>get(Commons.GAMEOVER_SKULL_IMG_PATH),
                    (float)Commons.WINDOW_WIDTH /2 -(float)game.am.<Texture>get(Commons.GAMEOVER_SKULL_IMG_PATH).getWidth()/2,
                    Commons.WINDOW_HEIGHT -350 - (float) game.am.<Texture>get(Commons.GAMEOVER_SKULL_IMG_PATH).getHeight() /2);

            game.textPrinter.draw(game.batch, "Score obtained: "+score, Commons.WORLD_X_START +100, Commons.WINDOW_HEIGHT -600);
            game.textPrinter.draw(game.batch, "Gameover Cause: "+gameoverText, Commons.WORLD_X_START +100, Commons.WINDOW_HEIGHT -630);
            game.textPrinter.draw(game.batch, "Press any key to go back to menu", Commons.WINDOW_WIDTH-500, Commons.WINDOW_HEIGHT -700);
        }
        game.batch.end();

        // fetching info from database
        try {
            ResultModelDAO resultModelDAO = new ConcreteResultModelDAO();

            ResultModel resultModelToInsert;
            if(game.username.isEmpty())
                resultModelToInsert = new ResultModel(Commons.DEFAULT_USERNAME, GameScreen.getScore(), GameScreen.getTime());
            else
                resultModelToInsert = new ResultModel(game.username, GameScreen.getScore(), GameScreen.getTime());

            resultModelDAO.insert(resultModelToInsert);
        }
        catch (SQLException e){
                game.batch.begin();
                game.textPrinter.draw(game.batch, "ERROR WHILE PUSHING DATA INTO DATABASE", Commons.WORLD_X_START+100, Commons.WINDOW_HEIGHT -700);
                game.batch.end();
        }

        time += delta;
        if(time >= 3 && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new MenuScreen(game));
            this.dispose();
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

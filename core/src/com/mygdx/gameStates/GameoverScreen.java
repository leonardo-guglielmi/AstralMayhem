package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.AstralMayhem;
import com.mygdx.utils.Commons;

public class GameoverScreen implements Screen {

    private final AstralMayhem game;
    private final String gameoverText;
    private final long score;

    public GameoverScreen(AstralMayhem game, String gameoverText, long score){
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
            game.batch.draw(game.am.<Texture>get("gameover/gameOverTitle.png"),
                    (float)Commons.WINDOW_WIDTH /2 -(float)game.am.<Texture>get("gameover/gameOverTitle.png").getWidth()/2,
                    Commons.WINDOW_HEIGHT -50 -(float)game.am.<Texture>get("gameover/gameOverTitle.png").getHeight()/2);

            game.batch.draw(game.am.<Texture>get("gameover/skull.png"),
                    (float)Commons.WINDOW_WIDTH /2 -(float)game.am.<Texture>get("gameover/skull.png").getWidth()/2,
                    Commons.WINDOW_HEIGHT -350 - (float) game.am.<Texture>get("gameover/skull.png").getHeight() /2);

            game.textPrinter.draw(game.batch, "Score obtained: "+score, Commons.WORLD_X_START +100, Commons.WINDOW_HEIGHT -600);
            game.textPrinter.draw(game.batch, "Cause: "+gameoverText, Commons.WORLD_X_START +100, Commons.WINDOW_HEIGHT -630);
            game.textPrinter.draw(game.batch, "click everywhere to go back to menu", Commons.WINDOW_WIDTH-500, Commons.WINDOW_HEIGHT -600);
        }
        game.batch.end();

        // when screen is touched change the game state
        if(Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
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

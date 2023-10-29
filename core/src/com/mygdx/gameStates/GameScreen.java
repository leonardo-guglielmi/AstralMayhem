package com.mygdx.gameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.AstralMayhem;
import com.mygdx.bullets.BulletManager;
import com.mygdx.characters.Enemy;
import com.mygdx.characters.EnemyManager;
import com.mygdx.characters.Hero;
import com.mygdx.displayable.DisplayObject;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private final AstralMayhem game;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final BulletManager bm = new BulletManager();
    private final EnemyManager em = new EnemyManager(bm);
    private final Hero hero = new Hero(new Texture(Gdx.files.internal("hero.png")), 0, 100, bm);

    public GameScreen(final AstralMayhem game){
        this.game = game;
        camera.setToOrtho(false);
        game.batch.setProjectionMatrix(camera.combined);

        em.addEnemy(new Enemy(new Texture(Gdx.files.internal("enemy.png")), 0, 500, bm));
        em.addEnemy(new Enemy(new Texture(Gdx.files.internal("enemy.png")), 300, 500, bm));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.begin();
        {
            DisplayObject dispHero = hero.getDisplayObject();
            game.batch.draw(dispHero.tx, dispHero.posX, dispHero.posY);

            ArrayList<DisplayObject> bulletsDisp = bm.getDisplayable();
            for(DisplayObject b : bulletsDisp)
                game.batch.draw(b.tx, b.posX, b.posY);

            ArrayList<DisplayObject> enemyDisp = em.getDisplayable();
            for(DisplayObject e : enemyDisp)
                game.batch.draw(e.tx, e.posX, e.posY);

        }
        game.batch.end();

        hero.handleInput();
        bm.update(delta);
        em.update();
        em.check();
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

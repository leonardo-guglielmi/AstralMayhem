package com.mygdx.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.manager.Managable;
import com.mygdx.displayable.DisplayObject;
import com.mygdx.displayable.Displayable;
import com.mygdx.manager.BulletManager;
import com.mygdx.enemyStuff.Strategy;
import com.mygdx.enemyStuff.StrategyContext;

public class Enemy implements Character, Displayable, Managable, StrategyContext {

    private Texture tx;
    private Rectangle body = new Rectangle();
    private int speed = 2;
    private BulletManager bm;
    private TypeOfCharacter type = TypeOfCharacter.ENEMY;
    private static float shootTimeGap = 3;
    public float timeElapsedSinceLastShoot;

    private Strategy strat;

    public Enemy(Texture tx, int startingX, int startingY, BulletManager bm){
        this.tx = tx;
        body.height = tx.getHeight();
        body.width = tx.getWidth();
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
        timeElapsedSinceLastShoot = 0;
    }

    @Override
    public DisplayObject getDisplayObject(){
        return new DisplayObject(tx, (int)body.x, (int)body.y);
    }

    @Override
    public void move() {
        this.move(1);
    }


    //todo: questa devo assicurarmi che non venga usata
    @Override
    public void move(int dir) {
        body.x += dir * speed;
        if (body.x < 0) {
            body.x = 0;
            speed = 1;
        }
        if (body.x > 860) {
            body.x = 860;
            speed = -1;
        }
    }

    @Override
    public void shoot() {
        timeElapsedSinceLastShoot += Gdx.graphics.getDeltaTime();
        if(timeElapsedSinceLastShoot >= shootTimeGap) {
            bm.addBullet((int) (body.x + body.width / 2), (int) (body.y + body.height / 2), -3, type);
            timeElapsedSinceLastShoot = 0;
        }
    }

    @Override
    public boolean checkCollision() {
        return bm.getBulletCollision(body, type) >= 1;
    }

    @Override
    public void setStrategy(Strategy strat){
        this.strat = strat;
    }

    public void exec(){
        strat.execute();
    }
}

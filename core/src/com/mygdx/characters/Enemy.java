package com.mygdx.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Commons;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.displayable.Displayable;
import com.mygdx.gameStates.TestScreen;
import com.mygdx.manager.BulletManager;
import com.mygdx.enemyStuff.Strategy;
import com.mygdx.enemyStuff.StrategyContext;

public class Enemy implements Character, Displayable, StrategyContext {
    private Texture tx;
    private Rectangle body = new Rectangle();
    private BulletManager bm;
    private TypeOfCharacter type = TypeOfCharacter.ENEMY;

    private Strategy strat;

    public Enemy(Texture tx, int startingX, int startingY, BulletManager bm){
        this.tx = tx;
        body.height = tx.getHeight();
        body.width = tx.getWidth();
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
    }

    public int getX(){
        return (int)body.x;
    }

    public int getY(){
        return (int)body.y;
    }

    public int getWidth(){
        return (int)body.width;
    }

    public int getHeight(){
        return (int)body.height;
    }

    @Override
    public DisplayableObject getDisplayableObject(){
        return new DisplayableObject(tx, (int)body.x, (int)body.y);
    }

    @Override
    public void disposeTexture(){
        tx.dispose();
    }

    @Override
    public void move(int x, int y) {
        body.x += x;
        body.y += y;
        if (body.x < Commons.HORIZONTAL_START)
            body.x = Commons.HORIZONTAL_START;
        if (body.x > Commons.HORIZONTAL_END -body.width)
            body.x = Commons.HORIZONTAL_END-body.width;
    }

    @Override
    public void shoot() {
        bm.addBullet((int) (body.x + body.width / 2), (int) (body.y + body.height / 2), -3, type);
    }

    @Override
    public int getNumCollisions() {
        return bm.getBulletCollision(body, type);
    }

    @Override
    public void setStrategy(Strategy strat){
        this.strat = strat;
    }

    public void update(){
        strat.execute();
    }

    public void teleport(){
        body.y -= 100;
    }
}

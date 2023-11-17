package com.mygdx.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Commons;
import com.mygdx.TypeOfEntity;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.displayable.Displayable;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.inputManagement.PlayerInputHandler;

/**
 * This class contains all the information about the Hero character
 */

public class Hero implements Character, Displayable {
    private int hp = 10;
    private int speed = 2;
    private TypeOfEntity type = TypeOfEntity.HERO;
    private Texture tx;
    private Rectangle body = new Rectangle();
    private PlayerInputHandler input = new PlayerInputHandler(this);

    private BulletManager bm;


    public Hero(Texture tx, int startingX, int startingY, BulletManager bm) {
        this.tx = tx;
        body.height = tx.getHeight();
        body.width = tx.getWidth();
        body.x = startingX;
        body.y = startingY;
        this.bm = bm;
    }

    public int getHp() {
        return hp;
    }

    public int getX(){
        return (int)body.x;
    }

    public int getY(){
        return (int)body.y;
    }

    @Override
    public DisplayableObject getDisplayableObject() {
        return new DisplayableObject(tx, (int) body.x, (int) body.y);
    }

    @Override
    public void disposeTexture() {
        tx.dispose();
    }


    @Override
    public void move(int x, int y) {
        body.x += x * speed;
        if (body.x < Commons.HORIZONTAL_START)
            body.x = Commons.HORIZONTAL_START;
        if (body.x > Commons.HORIZONTAL_END -body.width)
            body.x = Commons.HORIZONTAL_END-body.width;
    }

    @Override
    public void shoot() {
        bm.addBullet((int) (body.x + body.width / 4), (int) (body.y + body.height / 2), 10, type);
    }

    @Override
    public int getNumCollisions() {
        int damage = bm.getBulletCollision(body, type);
        hp -= damage;
        return damage;
    }

    /**
     * Handles user input to instruct the hero object to do some action
     */
    public void handleInput() {
        input.handle();
    }
}
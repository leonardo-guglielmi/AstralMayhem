package com.mygdx.bullets;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

// todo: trovare il modo di eliminare un proiettile
public class BulletManager {
    private ArrayList<Bullet> bulletSet = new ArrayList<>();

    public void addBullet(int startX, int startY, int vel, TypeOfCharacter source) {
        bulletSet.add(new Bullet(startX, startY, vel, source));
    }

    public void update(float delta){
        for(Bullet b : bulletSet){
            b.updatePosition(delta);
        }
    }

    public ArrayList<Texture> getBulletsTexture(){
        ArrayList<Texture> arrTx = new ArrayList<>();
        for(Bullet b : bulletSet)
            arrTx.add(b.getTexture());
        return arrTx;
    }
}

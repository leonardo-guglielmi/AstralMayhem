package com.mygdx.characters;

public interface Character {

    public void move();
    public void move(int dir);
    public void shoot();
    public boolean checkCollision();
}

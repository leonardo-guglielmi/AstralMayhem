package com.mygdx.characters;

public interface Character {

    public void move(); //todo: trovare il modo di unificare questi due metodi
    public void move(int dir);
    public void shoot();
    public boolean checkCollision(); //todo: sarebbe meglio fargli tornare un int
}

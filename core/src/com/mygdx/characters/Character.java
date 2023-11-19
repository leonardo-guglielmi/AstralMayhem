package com.mygdx.characters;

import com.badlogic.gdx.math.Vector2;

/**
 * This interface specifies the base behavior of any character in the game
 */
public interface Character {

    /**
     *  This method must update character position given certain direction
     * @param x horizontal movement
     * @param y vertical movement
     */
    void move(int x, int y);

    /**
     * This method must enable the character to shoot something
     */
    void shoot();

    /**
     * This method should return the number of object that are colliding with the character
     * @return the number of object the character is colliding
     */
    int getNumCollisions();
}
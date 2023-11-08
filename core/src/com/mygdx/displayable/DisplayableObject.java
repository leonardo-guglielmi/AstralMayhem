package com.mygdx.displayable;

import com.badlogic.gdx.graphics.Texture;

/**
 * Classe usata per trasportare le informazioni in sola lettura di un attore che deve essere stampato a schermo
 */
public class DisplayableObject {
    public Texture tx;
    public int posX;
    public int posY;

    /**
     * Costruttore di base che prende le 3 informazioni da stampare
     * @param tx Texture da stampare a schermo
     * @param posX coordinata orizzontale della texture
     * @param posY coordinata verticale della texture
     */
    public DisplayableObject(Texture tx, int posX, int posY){
        this.tx = tx;
        this.posX = posX;
        this.posY = posY;
    }
}

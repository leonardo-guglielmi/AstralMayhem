package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.characters.TypeOfCharacter;
import com.mygdx.displayable.DisplayableObject;
import com.mygdx.displayable.Displayable;

/**
 * Questa classe contiene tutte le informazioni che riguardano un singolo proiettile del gioco
 */
public class Bullet implements Displayable {
    private Rectangle body = new Rectangle(); //corpo fisico del proiettile
    private int vel; // velocità del proiettile, indica sia il verso che il modulo
    private Texture tx;
    private TypeOfCharacter source; // tipo di Character che ha generato il proiettile


    public Bullet(int startX, int startY, int vel, TypeOfCharacter source){
        tx = new Texture(Gdx.files.internal("bullet2.png"));
        body.width = tx.getWidth();
        body.height = tx.getHeight();
        body.x = startX;
        body.y = startY;
        this.vel = vel;
        this.source = source;
    }

    @Override
    public DisplayableObject getDisplayableObject(){
        return new DisplayableObject(tx, (int)body.x, (int)body.y);
    }

    public int getPosY(){
        return (int)body.y;
    }

    public TypeOfCharacter getSource(){
        return source;
    }

    // ritorna una copia del body per gestire le collisioni in sola lettura
    public Rectangle getBody(){
        return new Rectangle(body);
    }

    // aggiorna la posizione del proiettile
    public void updatePosition(){
        body.y += vel;
    }

    // controlla se si sta scontrando con un altro corpo e, nel caso, se i due hanno source diverso
    public boolean isHitting(Rectangle body, TypeOfCharacter type){
        return this.body.overlaps(body) && this.source != type;
    }
}

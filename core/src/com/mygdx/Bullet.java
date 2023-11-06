package com.mygdx;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.characters.TypeOfCharacter;
import com.mygdx.displayable.DisplayObject;
import com.mygdx.displayable.Displayable;
import org.jetbrains.annotations.NotNull;

public class Bullet implements Displayable {
    public Rectangle body = new Rectangle();
    private int vel;
    private Texture tx;
    private TypeOfCharacter source;


    public Bullet(int startX, int startY, int vel, TypeOfCharacter source){
        tx = new Texture(Gdx.files.internal("bullet.png"));
        body.width = 8;
        body.height = 8;
        body.x = startX;
        body.y = startY;
        this.vel = vel;
        this.source = source;
    }

    @Override
    public DisplayObject getDisplayObject(){
        return new DisplayObject(tx, (int)body.x, (int)body.y);
    }

    public int getPosY(){
        return (int)body.y;
    }

    public TypeOfCharacter getSource(){
        return source;
    }

    public void updatePosition(){
        body.y += vel;
    }

    public boolean isHitting(Rectangle body, TypeOfCharacter type){
        return this.body.overlaps(body) && this.source != type;
    }
}

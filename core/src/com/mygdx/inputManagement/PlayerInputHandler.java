package com.mygdx.inputManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.Hero;

public class PlayerInputHandler implements InputHandler{
    private final MoveRightCommand mvRight;
    private final MoveLeftCommand mvLeft;
    private final Hero h;

    public PlayerInputHandler(Hero h){
        this.h = h;
        mvRight = new MoveRightCommand(h);
        mvLeft = new MoveLeftCommand(h);
    }

    @Override
    public void handle(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            mvRight.execute();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            mvLeft.execute();
    }
}

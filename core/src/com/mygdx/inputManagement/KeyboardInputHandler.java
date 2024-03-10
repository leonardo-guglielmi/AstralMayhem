package com.mygdx.inputManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.entities.Hero;

public class KeyboardInputHandler implements InputHandler{
    private final Command mvRight;
    private final Command mvLeft;

    private final Command shootCmd;

    public KeyboardInputHandler(Hero h){
        mvRight = new MoveRightCommand(h);
        mvLeft = new MoveLeftCommand(h);
        shootCmd = new ShootCommand(h);
    }

    @Override
    public void handle(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            mvRight.execute();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            mvLeft.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            shootCmd.execute();

    }
}

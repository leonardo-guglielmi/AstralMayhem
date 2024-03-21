package com.mygdx.inputManagement.menuManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.AstralMayhem;
import com.mygdx.inputManagement.Command;
import com.mygdx.inputManagement.InputHandler;

public class KeyboardMenuInputHandler implements InputHandler {


    //todo: da aggiungere il comandi per visualizzare i tuoi punteggi, i migliori punteggi e per inserire un username
    Command startCmd;
    Command quitCmd;

    public KeyboardMenuInputHandler(AstralMayhem game){
        startCmd = new StartGameCommand(game);
        quitCmd = new QuitAppCommand();

    }

    @Override
    public void handle() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.S))
            startCmd.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
            quitCmd.execute();

    }
}

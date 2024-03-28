package com.mygdx.inputManagement.menuManagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.AstralMayhem;
import com.mygdx.gameStates.MenuScreen;
import com.mygdx.inputManagement.Command;
import com.mygdx.inputManagement.InputHandler;
import com.mygdx.utils.TextInputProcessor;

public class KeyboardMenuInputHandler implements InputHandler {


    Command startCmd;
    Command quitCmd;
    Command startReadCmd;
    Command stopReadCmd;
    Command loadBestCmd;
    Command loadUserCmd;
    TextInputProcessor tip;


    public KeyboardMenuInputHandler(AstralMayhem game, MenuScreen ms, TextInputProcessor tip){
        startCmd = new StartGameCommand(game);
        quitCmd = new QuitAppCommand();
        this.tip = tip;
        startReadCmd = new StartReadingTextCommand(tip);
        stopReadCmd = new StopReadingTextCommand(tip);
        loadBestCmd = new LoadBestResultsCommand(ms);
        loadUserCmd = new LoadUserResultsCommand(ms);
    }

    @Override
    public void handle() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) && !tip.isReading())
            startCmd.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q) && !tip.isReading())
            quitCmd.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.T))
            startReadCmd.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            stopReadCmd.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.B))
            loadBestCmd.execute();

        if(Gdx.input.isKeyJustPressed(Input.Keys.Y))
            loadUserCmd.execute();
    }
}

package com.mygdx.inputManagement.menuManagement;

import com.mygdx.inputManagement.Command;
import com.mygdx.utils.TextInputProcessor;

public class StartReadingTextCommand implements Command {

    private final TextInputProcessor tip;

    public StartReadingTextCommand(TextInputProcessor tip){
        this.tip = tip;
    }

    @Override
    public void execute() {
        tip.startReading();
    }
}

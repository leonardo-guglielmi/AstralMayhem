package com.mygdx.inputManagement.menuManagement;

import com.mygdx.inputManagement.Command;
import com.mygdx.utils.TextInputProcessor;

public class StopReadingTextCommand implements Command {
    private final TextInputProcessor tip;

    public StopReadingTextCommand(TextInputProcessor tip){
        this.tip = tip;
    }

    @Override
    public void execute() {
        tip.stopReading();
    }
}

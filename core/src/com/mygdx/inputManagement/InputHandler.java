package com.mygdx.inputManagement;

/**
 *  Base interface for chain of responsibility pattern
 */

public interface InputHandler {

    /**
     * This method must handle all input from user
     */
    void handle( );
}

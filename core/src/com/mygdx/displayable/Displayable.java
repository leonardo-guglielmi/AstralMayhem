package com.mygdx.displayable;

/**
 *  This interface specifies the behavior of something tha can be rendered on screen
 */
public interface Displayable {
    /**
     * This method must return all the information to render an object
     * @return information to render
     */
    DisplayableObject getDisplayableObject();
}

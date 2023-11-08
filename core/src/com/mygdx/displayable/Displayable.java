package com.mygdx.displayable;

/**
 *  Questa interfaccia deve essere ereditata da quelle classi che devono essere stampate a schermo
 */
public interface Displayable {
    /**
     * Questo metodo deve essere implementato in modo da fornire in sola lettura le informazioni sta stampare a schermo
     * @return le informazioni da stampare a schermo
     */
    DisplayableObject getDisplayableObject();
}

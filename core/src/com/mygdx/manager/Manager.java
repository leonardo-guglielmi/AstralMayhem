package com.mygdx.manager;

import com.mygdx.displayable.DisplayableObject;

import java.util.ArrayList;

public interface Manager {
    //public void add(Managable m);
    public ArrayList<DisplayableObject> getDisplayable();
    public void update();
}

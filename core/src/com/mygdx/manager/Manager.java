package com.mygdx.manager;

import com.mygdx.displayable.DisplayObject;

import java.util.ArrayList;

public interface Manager {
    //public void add(Managable m);
    public ArrayList<DisplayObject> getDisplayable();
    public void update();
}

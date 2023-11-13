package com.mygdx.manager;

import com.mygdx.displayable.DisplayableObject;

import java.util.ArrayList;

public interface Manager {
    ArrayList<DisplayableObject> getDisplayable();
    void update();
}

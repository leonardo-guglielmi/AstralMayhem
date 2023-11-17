package com.mygdx.entityManagement;

import com.mygdx.displayable.DisplayableObject;

import java.util.ArrayList;

public interface Manager {
    ArrayList<DisplayableObject> getDisplayable();
    void update();
}

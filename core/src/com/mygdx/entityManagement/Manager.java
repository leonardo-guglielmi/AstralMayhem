package com.mygdx.entityManagement;

import java.util.AbstractMap;
import java.util.ArrayList;

public interface Manager {
    ArrayList<AbstractMap.SimpleEntry<Float, Float>> getPosition();
    void updateEntities();
}

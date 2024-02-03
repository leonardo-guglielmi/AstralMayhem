package com.mygdx.entityManagement;

import com.mygdx.utils.Pair;

import java.util.ArrayList;

public interface Manager {
    ArrayList<Pair<Float, Float>> getPrintInfo();
    void updateEntities();
}

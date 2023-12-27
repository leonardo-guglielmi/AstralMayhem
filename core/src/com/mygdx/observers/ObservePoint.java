package com.mygdx.observers;

import java.util.ArrayList;
import java.util.List;

public class ObservePoint {
    private final List<Observer> obsList = new ArrayList<>();

    public void addObserver(Observer o){
        obsList.add(o);
    }

    public void notifyObservers(){
        for(Observer o : obsList)
            o.observe();
    }

}

package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.jogamp.opengl.util.stereo.generic.GenericStereoDeviceConfig;

public class Timer {
    private final float timeLatency;
    private float lastTimestamp;

    public Timer(float timeLatency){
        this.timeLatency = timeLatency;
        lastTimestamp = Gdx.graphics.getDeltaTime();
    }

    public boolean check(){
        float actualTimestamp = Gdx.graphics.getDeltaTime();
        if(actualTimestamp - lastTimestamp > timeLatency){
            lastTimestamp = actualTimestamp;
            return true;
        }
        else{
            return false;
        }
    }
}

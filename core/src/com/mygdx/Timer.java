package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.jogamp.opengl.util.stereo.generic.GenericStereoDeviceConfig;

public class Timer {

    // gap time set during the creation of the timer, represents the duration of the timer
    private final float timeLatency;

    // last timestamp acquired
    private float timeElapsed = 0;

    public Timer(float timeLatency){
        this.timeLatency = timeLatency;
    }

    /**
     * Check if the timer is elapsed, in that cas notifies and resets  the timer
     * @return if the timer is ended
     */
    public boolean check(){
        timeElapsed += Gdx.graphics.getDeltaTime();
        if(timeElapsed > timeLatency){
            timeElapsed = 0;
            return true;
        }
        else{
            return false;
        }
    }
}

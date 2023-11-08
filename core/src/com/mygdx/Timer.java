package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.jogamp.opengl.util.stereo.generic.GenericStereoDeviceConfig;

public class Timer {
    /**
     * Gap temporale impostato al timer durante la sua creazione, mi dice dopo quanto tempo si azzera e ricomincia a contare
     */
    private final float timeLatency;

    /**
     * Ultimo timestamp acquisito
     */
    private float lastTimestamp;

    public Timer(float timeLatency){
        this.timeLatency = timeLatency;
        lastTimestamp = Gdx.graphics.getDeltaTime();
    }

    /**
     * Controlla il tempo passato dall'ultima chiamata del metodo, se è così ritorna vero altrimenti false
     * @return se è passato il timeLatency dall'ultima chiamata di questo metodo
     */
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

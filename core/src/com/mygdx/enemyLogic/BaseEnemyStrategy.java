package com.mygdx.enemyLogic;

import com.mygdx.utils.Commons;
import com.mygdx.utils.Timer;
import com.mygdx.entities.Enemy;

public class BaseEnemyStrategy implements Strategy{
    private final Enemy e;
    private int dirX = 1;
    private int dirY = 0;
    private final Timer shootTimer;

    public BaseEnemyStrategy(Enemy e, int t){
        this.e = e;
        this.shootTimer = new Timer(t);
    }

    @Override
    public void execute() {

        executeFirstPart();

        if(shootTimer.check())
            e.shoot();
    }

    /*Ho dovuto spezzare il metodo execute in due sottoparti. Una è rimasta lì dentro e controlla il timer,
    mentre l'altra è il metodo excuteFirstPart() qui sotto, e gestisce la posizione.
    Ho dovuto fare così perché sennò c'erano problemi negli unit test. In particolare
    inizialmente volevo testare il metodo execute() completo, ma questo non era possibile, perché
    il timer nei test non funziona bene, visto che si attiva con l'avvio del programma. Ecco che spezzando il metodo, non si
    fa il test di tutto (quell'if con il timer non era di interesse da testare), ma solo della parte effettivamente di interesse,
    ovvero quella spostata all'interno di executeFirstPart()
    */
    public void executeFirstPart(){
        if(e.getX() >= Commons.WORLD_X_END -e.getWidth() || e.getX() < Commons.WORLD_X_START){
            dirX *= -1;
            dirY = -100;
        }
        else if(dirY != 0){
            dirY = 0;
        }
        e.move(dirX, dirY);
    }


    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }


}

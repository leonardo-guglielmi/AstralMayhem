package enemyLogicTest;

import com.mygdx.enemyLogic.BaseEnemyStrategy;
import com.mygdx.entities.Enemy;
import com.mygdx.entities.TypeOfEnemy;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.utils.Commons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestBaseStrategy extends BaseEnemyStrategy{

    public TestBaseStrategy(Enemy e, int t) {
        super(e, t);
    }

    public void testExecute(){
        executeFirstPart();
    }
}

public class BaseEnemyStrategyTest {
    @Test
    void testExecuteFirstPart(){
        //NOTA: non si può testare il metodo execute() completo perché il timer in fase di test dà problemi-
        BulletManager bm = new BulletManager();


        //Test del caso standard (nemico non è vicino a nessun bordo)
        Enemy e  = new Enemy( Commons.WORLD_X_START+50 , Commons.WORLD_Y_START+50, bm, TypeOfEnemy.BASE);
        TestBaseStrategy bes = new TestBaseStrategy(e,100);
        Assertions.assertEquals(1, bes.getDirX());
        Assertions.assertEquals(0,bes.getDirY());

        bes.testExecute();

        Assertions.assertEquals(Commons.WORLD_X_START+50+1,e.getX());
        Assertions.assertEquals(Commons.WORLD_X_START+50,e.getY());

        //Caso in cui il nemico è arrivato al bordo destro della schermata
        Enemy e1  = new Enemy( Commons.WORLD_X_END , Commons.WORLD_Y_START+200, bm, TypeOfEnemy.ADVANCED);
        TestBaseStrategy bes1 = new TestBaseStrategy(e1,100);
        Assertions.assertEquals(1, bes1.getDirX());
        Assertions.assertEquals(0,bes1.getDirY());

        bes1.testExecute();
        Assertions.assertEquals(-1, bes1.getDirX());
        Assertions.assertEquals(-100, bes1.getDirY());


        //Caso in cui il nemico è prima del bordo sinistro di inizio schermata
        Enemy e2  = new Enemy( Commons.WORLD_X_START -5, Commons.WORLD_Y_START, bm, TypeOfEnemy.ADVANCED);
        TestBaseStrategy bes2 = new TestBaseStrategy(e2,100);
        Assertions.assertEquals(1, bes2.getDirX());
        Assertions.assertEquals(0,bes2.getDirY());

        bes2.testExecute();
        Assertions.assertEquals(-1, bes2.getDirX());
        Assertions.assertEquals(-100, bes2.getDirY());
    }
}

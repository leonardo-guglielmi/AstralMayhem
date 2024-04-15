package entityManagementTest;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entities.TypeOfEntity;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.utils.Commons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BulletManagerTest {

    // test per l'aggiunta di un Bullet
    @Test
    void addBulletTest(){
        BulletManager bm = new BulletManager();
        bm.addBullet(0,0,0, TypeOfEntity.HERO);
        Assertions.assertEquals(1, bm.getNumBullet());
    }


    // test getNumCollision
    @Test
    void getBulletCollisionTest(){
        BulletManager bm = new BulletManager();
        bm.addBullet(0,0,0, TypeOfEntity.HERO);
        Rectangle r = new Rectangle();
        r.width = 10;
        r.height = 10;

        r.x = 10;
        r.y = 10;
        Assertions.assertEquals(0, bm.getBulletCollision(r, TypeOfEntity.ENEMY));

        r.x = 0;
        r.y = 0;
        Assertions.assertEquals(0, bm.getBulletCollision(r, TypeOfEntity.HERO));
        Assertions.assertEquals(1, bm.getBulletCollision(r, TypeOfEntity.ENEMY));
    }

    // test checkMutualCollision
    @Test
    void checkMutualCollision(){
        BulletManager bm = new BulletManager();
        bm.addBullet(Commons.WORLD_X_START,Commons.WORLD_Y_START,0,TypeOfEntity.HERO);
        bm.addBullet(Commons.WORLD_X_START,Commons.WORLD_Y_START,0,TypeOfEntity.HERO);
        bm.updateEntities();
        Assertions.assertEquals(2, bm.getNumBullet());
        bm.addBullet(Commons.WORLD_X_START,Commons.WORLD_Y_START,0, TypeOfEntity.ENEMY);
        bm.updateEntities();
        Assertions.assertEquals(0, bm.getNumBullet());
    }

    // test check borders
    @Test
    void checkBorders(){
        BulletManager bm = new BulletManager();
        bm.addBullet(Commons.WORLD_X_START, Commons.WORLD_Y_START, 0, TypeOfEntity.ENEMY);
        bm.updateEntities();
        Assertions.assertEquals(1, bm.getNumBullet());

        bm = new BulletManager();
        bm.addBullet(Commons.WORLD_X_START, Commons.WORLD_Y_START-100, 0, TypeOfEntity.ENEMY);
        bm.updateEntities();
        Assertions.assertEquals(0, bm.getNumBullet());

        bm = new BulletManager();
        bm.addBullet(Commons.WORLD_X_START, Commons.WORLD_Y_END+100, 0, TypeOfEntity.ENEMY);
        bm.updateEntities();
        Assertions.assertEquals(0, bm.getNumBullet());
    }
}

package entitiesTest;

import com.mygdx.entities.Enemy;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.utils.Commons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class EnemyTest {

    @Test
    void testShoot(){
        BulletManager bm = new BulletManager();
        Enemy e  = new Enemy( Commons.WORLD_X_START , Commons.WORLD_Y_START, bm);
        Assertions.assertTrue(bm.isBulletSetEmpty());
        e.shoot();
        Assertions.assertFalse(bm.isBulletSetEmpty());
    }

    @Test
    void testMove(){

        //Test of basic update of position of X and Y
        int dx = 10;
        int dy = 10;
        BulletManager bm = new BulletManager();
        Enemy e  = new Enemy( Commons.WORLD_X_START , Commons.WORLD_Y_START, bm);

        Assertions.assertEquals(Commons.WORLD_X_START, e.getX());
        Assertions.assertEquals(Commons.WORLD_Y_START, e.getY());

        e.move(dx,dy);

        Assertions.assertEquals(Commons.WORLD_X_START+10, e.getX());
        Assertions.assertEquals(Commons.WORLD_Y_START+10, e.getY());

        /*Test the correction of invalid X position of Enemy (if behind the left border of the map,
          the enemy is teleported to the beginning of the map)
         */
        int dx1 = 10;
        Enemy e1  = new Enemy( Commons.WORLD_X_START-100, Commons.WORLD_Y_START, bm);
        Assertions.assertEquals(Commons.WORLD_X_START-100, e1.getX());
        e1.move(dx1,0);
        Assertions.assertEquals(Commons.WORLD_X_START, e1.getX());


        /*Test the correction of invalid X position of Enemy (if beyond the right border of the map,
          the enemy is teleported to the beginning of the map)
         */
        int dx2 = 10;
        Enemy e2  = new Enemy( Commons.WORLD_X_END, Commons.WORLD_Y_START, bm);
        Assertions.assertEquals(Commons.WORLD_X_END, e2.getX());
        e2.move(e2.getWidth()+dx2,0);
        Assertions.assertEquals(Commons.WORLD_X_END-e2.getWidth(),e2.getX());


    }
}

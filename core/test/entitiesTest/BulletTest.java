package entitiesTest;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entities.Bullet;
import com.mygdx.entities.TypeOfEntity;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.utils.Commons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class BulletTest {
    @Test
    void testIsHitting(){
        /*Si testa che il tipo di entità che ha generato il proiettile e quello che
        collide influiscano correttamente sulla determinazione della collisione
         */

        //NON FUNZIONA PER ORA

        Bullet b = new Bullet(Commons.WORLD_X_START, Commons.WORLD_Y_START, 3, TypeOfEntity.HERO);
        TypeOfEntity toe = TypeOfEntity.HERO;
        Rectangle r = new Rectangle(b.getBody());
        Assertions.assertFalse(b.isHitting(r,toe));

        Bullet b1 = new Bullet(Commons.WORLD_X_START, Commons.WORLD_Y_START, 3, TypeOfEntity.HERO);
        TypeOfEntity toe1 = TypeOfEntity.EARTH;
        Rectangle r1 = new Rectangle(b1.getBody());
        Assertions.assertTrue(b.isHitting(r1,toe1));

        Bullet b2 = new Bullet(Commons.WORLD_X_START, Commons.WORLD_Y_START, 3, TypeOfEntity.HERO);
        TypeOfEntity toe2 = TypeOfEntity.ENEMY;
        Rectangle r2 = new Rectangle(b2.getBody());
        Assertions.assertTrue(b.isHitting(r2,toe2));

        Bullet b3 = new Bullet(Commons.WORLD_X_START, Commons.WORLD_Y_START, 3, TypeOfEntity.ENEMY);
        TypeOfEntity toe3 = TypeOfEntity.HERO;
        Rectangle r3 = new Rectangle(b3.getBody());
        Assertions.assertTrue(b.isHitting(r3,toe3));

        Bullet b4 = new Bullet(Commons.WORLD_X_START, Commons.WORLD_Y_START, 3, TypeOfEntity.ENEMY);
        TypeOfEntity toe4 = TypeOfEntity.ENEMY;
        Rectangle r4 = new Rectangle(b4.getBody());
        Assertions.assertFalse(b.isHitting(r4,toe4));

        Bullet b5 = new Bullet(Commons.WORLD_X_START, Commons.WORLD_Y_START, 3, TypeOfEntity.ENEMY);
        TypeOfEntity toe5 = TypeOfEntity.EARTH;
        Rectangle r5 = new Rectangle(b5.getBody());
        Assertions.assertTrue(b.isHitting(r5,toe5));
    }



}

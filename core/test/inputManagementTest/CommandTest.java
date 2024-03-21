package inputManagementTest;

import com.mygdx.entities.Hero;
import com.mygdx.entityManagement.BulletManager;
import com.mygdx.inputManagement.playerManagement.MoveLeftCommand;
import com.mygdx.inputManagement.playerManagement.MoveRightCommand;
import com.mygdx.utils.Commons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CommandTest {

    @Test
    void testMoveRightCommand(){
        Hero h  = new Hero( Commons.WORLD_X_START, 0, null);
        MoveRightCommand mrc = new MoveRightCommand(h);
        mrc.execute();
        Assertions.assertEquals(Commons.WORLD_X_START+10, h.getX());

    }
    @Test
    void testMoveLeftCommand(){
        Hero h  = new Hero( Commons.WORLD_X_START +10, 0, null);
        MoveLeftCommand mlc = new MoveLeftCommand(h);
        mlc.execute();
        Assertions.assertEquals(Commons.WORLD_X_START, h.getX());
    }


    // class added to be used in the test
    private class TestBulletManager extends BulletManager{
        public int numElements(){
            return bulletSet.size();
        }
    }
    @Test
    void testShootCommand(){
        //BulletManager bm = new BulletManager();
    }
}

package inputManagementTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.entities.Hero;
import com.mygdx.inputManagement.MoveLeftCommand;
import com.mygdx.inputManagement.MoveRightCommand;
import com.mygdx.inputManagement.ShootCommand;
import com.mygdx.utils.Commons;
import com.mygdx.utils.MyAssetManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testo che l'implementazione affettiva dei vari Command sia corretta
 */

public class CommandTest {

    // i parametri am e bm non sono rilevanti al fine di questo test
    Hero h;
    MoveLeftCommand mlc;
    MoveRightCommand mrc;
    ShootCommand sc;

    private void setup(){
        h  = new Hero(new Texture(new FileHandle("hero.png")), 6, 0, null);
        mlc = new MoveLeftCommand(h);
        mrc = new MoveRightCommand(h);
        sc = new ShootCommand(h);

    }

    @Test
    void testAllCommands(){
        setup();

        mlc.execute();
        Assertions.assertEquals(h.getX(), 0);

        mrc.execute();
        Assertions.assertEquals(h.getX(), 6);
    }

}

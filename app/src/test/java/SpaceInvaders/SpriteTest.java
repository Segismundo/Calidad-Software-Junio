package SpaceInvaders;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpriteTest {
    @Test
    public void CollitionTrue() {
        Sprite s1 = new Sprite();
        Sprite s2 = new Sprite();
        s1.setX(2);
        s2.setX(2);
        s1.setY(4);
        s2.setY(4);
        s1.setWidth(1);
        s2.setWidth(1);
        s1.setHeight(1);
        s2.setHeight(1);
        assertTrue(s1.isCollition(s2));
    }

    @Test
    public void CollitionFalse() {
        Sprite s1 = new Sprite();
        Sprite s2 = new Sprite();
        s1.setX(2);
        s2.setX(5);
        s1.setY(4);
        s2.setY(4);
        s1.setWidth(1);
        s2.setWidth(1);
        s1.setHeight(1);
        s2.setHeight(1);
        assertFalse(s1.isCollition(s2));
    }
}

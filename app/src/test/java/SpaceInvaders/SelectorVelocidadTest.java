package SpaceInvaders;

import org.junit.Assert;
import org.junit.Test;

public class SelectorVelocidadTest {
    @Test
    public void TestVelocidad()
    {
        SelectorVelocidad selectorVelocidad = new SelectorVelocidad();
        Assert.assertEquals(true, selectorVelocidad.esVelocidadValida(1));

        //Assert.assertEquals(true, selectorVelocidad.esVelocidadValida(30));
    }
}

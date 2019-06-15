package spaceinvaders;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameView
{

	@Test
	public void TestMethods()
	{
		GameView gameView = new GameView(null, null);
		gameView.createInvaderSpaceFleet(1);
		assertNotNull(gameView.invaderSpaceFleet);
	}

}

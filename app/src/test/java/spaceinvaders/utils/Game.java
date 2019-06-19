package spaceinvaders.utils;

import spaceinvaders.GoodSpaceShip;
import spaceinvaders.InvaderSpaceShip;
import spaceinvaders.MotherShip;
import spaceinvaders.Shield;
import spaceinvaders.Shoot;
import spaceinvaders.Sprite;

public class Game
{

	private InvaderSpaceShip invader;
	private GoodSpaceShip goodSpaceShip;
	private MotherShip motherShip;
	private Shield shield;
	private Shoot goodSpaceShipShoot;
	private Shoot invaderSpaceShipShoot;
	private int scoreValue = 0;
	private int scoreBest3;

	public Game()
	{
		createGoodSpaceShip();
		createInvader();
		createMotherInvader();
		createShield();
	}

	// Creates a good ship
	private void createGoodSpaceShip()
	{
		this.goodSpaceShip = new GoodSpaceShip(50, 5);
	}

	// Creates an invader ship
	private void createInvader()
	{
		this.invader = new InvaderSpaceShip(50, 80, 50);
	}

	public void createMotherInvader()
	{
		this.motherShip = new MotherShip(50, 95, 3000);
	}

	private void createShield()
	{
		int numberOfWalls = 3;
		shield = new Shield(numberOfWalls);
	}

	//Creates a good ship shoot
	private void createGoodSpaceShipShoot(boolean alive)
	{
		int spriteColumns = 2;
		int spriteRows = 1;
		int x = goodSpaceShip.getX() + goodSpaceShip.getWidth() / 2 - (bmpGoodShoot.getWidth() / spriteColumns) / 2;
		int y = goodSpaceShip.getY();
		int xSpeed = goodSpaceShip.getXSpeed();
		this.goodSpaceShipShoot = new Shoot(this, bmpGoodShoot, spriteRows, spriteColumns, x, y, xSpeed, false, alive);
	}

	//Creates a invader ship shoot
	private void createInvaderSpaceShipShoot(boolean alive)
	{
		int spriteColumns = 9;
		int spriteRows = 1;
		Sprite shooterShip = invaderSpaceFleet.getShooter();
		int x = shooterShip.getX() + shooterShip.getWidth() / 2 - (bmpInvaderShoot.getWidth() / spriteColumns) / 2;
		int y = shooterShip.getY();
		this.invaderSpaceShipShoot = new Shoot(this, bmpInvaderShoot, spriteRows, spriteColumns, x, y, 0, true, alive);
	}

	public void killInvader()
	{
		this.invader.setAlive(false);
	}
}

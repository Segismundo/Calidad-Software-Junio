package spaceinvaders.utils;

import spaceinvaders.GoodSpaceShip;
import spaceinvaders.InvaderSpaceShip;
import spaceinvaders.MotherShip;
import spaceinvaders.Shield;
import spaceinvaders.Shoot;

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

    public Game(){
    }

    public void start(){
        createGoodSpaceShip();
        createInvader();
        createMotherInvader();
        createShield();
    }

    private void createGoodSpaceShip(){
        this.goodSpaceShip = new GoodSpaceShip(50,5);
    }

    private void createInvader(){
        this.invader = new InvaderSpaceShip(50,80,50);
    }

    private void createMotherInvader(){
        this.motherShip = new MotherShip(50,95,3000);
    }

	private void createShield()
	{
		int numberOfWalls = 3;
		shield = new Shield(numberOfWalls);
	}

    private void createGoodSpaceShipShoot(int x, int y) {
        this.goodSpaceShipShoot = new Shoot(x,y,false,true);
    }

    private void createInvaderSpaceShipShoot(int x, int y) {
        this.invaderSpaceShipShoot = new Shoot(x,y,true,true);
    }

    public void killInvader(){
        this.invader.setAlive(false);
    }





    //
    //
    //   Getter and Setter
    //
    //

    public GoodSpaceShip getGoodSpaceShip() {
        return goodSpaceShip;
    }

    public void setGoodSpaceShip(GoodSpaceShip goodSpaceShip) {
        this.goodSpaceShip = goodSpaceShip;
    }

    public InvaderSpaceShip getInvader() {
        return invader;
    }

    public void setInvader(InvaderSpaceShip invader) {
        this.invader = invader;
    }

    public MotherShip getMotherShip() {
        return motherShip;
    }

    public void setMotherShip(MotherShip motherShip) {
        this.motherShip = motherShip;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public Shoot getGoodSpaceShipShoot() {
        return goodSpaceShipShoot;
    }

    public void setGoodSpaceShipShoot(Shoot goodSpaceShipShoot) {
        this.goodSpaceShipShoot = goodSpaceShipShoot;
    }

    public Shoot getInvaderSpaceShipShoot() {
        return invaderSpaceShipShoot;
    }

    public void setInvaderSpaceShipShoot(Shoot invaderSpaceShipShoot) {
        this.invaderSpaceShipShoot = invaderSpaceShipShoot;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getScoreBest3() {
        return scoreBest3;
    }

    public void setScoreBest3(int scoreBest3) {
        this.scoreBest3 = scoreBest3;
    }
}

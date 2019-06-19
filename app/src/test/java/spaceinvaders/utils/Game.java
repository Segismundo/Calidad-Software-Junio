package spaceinvaders.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import spaceinvaders.Explosion;
import spaceinvaders.GoodSpaceShip;
import spaceinvaders.InvaderSpaceFleet;
import spaceinvaders.InvaderSpaceShip;
import spaceinvaders.MotherShip;
import spaceinvaders.R;
import spaceinvaders.Shield;
import spaceinvaders.Shoot;
import spaceinvaders.Sprite;

public class Game {

    private InvaderSpaceShip invader;
    private GoodSpaceShip goodSpaceShip;
    private MotherShip motherShip;
    private Shield shield;
    private int scoreValue=0;
    private int scoreBest3;

    public Game(){

    }

    // Creates a good ship
    private void createGoodSpaceShip(){
        this.goodSpaceShip = (GoodSpaceShip) new Sprite();
    }

    // Creates an invader ship
    private void createInvader(){
        this.invader = (InvaderSpaceShip) new Sprite();
    }

    public void createMotherInvader(){
        this.motherShip = (MotherShip) new Sprite();
    }

    private void createShield(){
        int horizontalNumOfBrick = 8;
        int verticalNumOfBrick = 4;
        int spriteColumns = 4;
        int numberOfWalls = 3;

        int wallWidth = bmpBrick.getWidth()/spriteColumns * horizontalNumOfBrick;
        int separation = (this.getWidth() - wallWidth)/(numberOfWalls-1);

        int x_ini = 0;
        int y_ini = goodSpaceShip.getY() - bmpBrick.getWidth() - 10;

        for(int i = 0; i<numberOfWalls;i++){
            shield = new Shield(this,bmpBrick,spriteColumns,horizontalNumOfBrick,verticalNumOfBrick,x_ini,y_ini,numberOfWalls,separation);
        }
    }

    public void killInvader(){
        this.invader.setAlive(false);
    }
}

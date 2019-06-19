package spaceinvaders.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import spaceinvaders.Explosion;
import spaceinvaders.GoodSpaceShip;
import spaceinvaders.InvaderSpaceFleet;
import spaceinvaders.R;
import spaceinvaders.Shield;
import spaceinvaders.Shoot;
import spaceinvaders.Sprite;

public class Game {

    private InvaderSpaceFleet invaderSpaceFleet;
    private boolean firstStart = true;
    private Explosion explosion;
    private GoodSpaceShip goodSpaceShip;
    private Shoot goodSpaceShipShoot;
    private Shoot invaderSpaceShipShoot;
    private Shield shield;
    private long lastClick;
    private int level = 1;
    private int scoreValue=0;
    private int scoreBest3;
    private int gameState = 1;

    public Game(){

    }

    // Creates a good ship
    private void createGoodSpaceShip(){
        this.goodSpaceShip = (GoodSpaceShip) new Sprite(82,82,500,1000-82);
    }

    // Creates an invader ship
    private void createInvaderSpaceFleet(int level){
        int landingHeight = goodSpaceShip.getHeight();
        this.invaderSpaceFleet = new InvaderSpaceFleet(this,this.getHeight()-landingHeight,level);
    }

    //Creates a good ship shoot
    private void createGoodSpaceShipShoot(boolean alive) {
        int spriteColumns = 2;
        int spriteRows = 1;
        int x = goodSpaceShip.getX()+goodSpaceShip.getWidth()/2 - (bmpGoodShoot.getWidth()/spriteColumns) / 2;
        int y = goodSpaceShip.getY();
        int xSpeed = goodSpaceShip.getXSpeed();
        this.goodSpaceShipShoot = new Shoot(this,bmpGoodShoot,spriteRows,spriteColumns,x,y,xSpeed,false,alive);
    }

    //Creates a invader ship shoot
    private void createInvaderSpaceShipShoot(boolean alive) {
        int spriteColumns = 9;
        int spriteRows = 1;
        Sprite shooterShip = invaderSpaceFleet.getShooter();
        int x = shooterShip.getX()+shooterShip.getWidth()/2 - (bmpInvaderShoot.getWidth()/spriteColumns) / 2;
        int y = shooterShip.getY();
        this.invaderSpaceShipShoot = new Shoot(this,bmpInvaderShoot,spriteRows,spriteColumns,x,y,0,true,alive);
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

    private void checkCollisions() {
        //Collision good ship shoot vs invader fleet
        if (goodSpaceShipShoot.isAlive()){

            if(invaderSpaceFleet.isCollision(this.goodSpaceShipShoot)){
                scoreValue = scoreValue + invaderSpaceFleet.getPoints();
                goodSpaceShipShoot.setAlive(false);
                createExplosion(true,true);
            }
        }

        //Collision good ship shoot vs invader ship shoot
        if(goodSpaceShipShoot.isAlive() && invaderSpaceShipShoot.isAlive()){
            if(invaderSpaceShipShoot.isCollition(this.goodSpaceShipShoot)){
                this.invaderSpaceShipShoot.setAlive(false);
                this.goodSpaceShipShoot.setAlive(false);
            }
        }

        //Collision good ship       vs invader ship shoot
        if(invaderSpaceShipShoot.getBottom()>goodSpaceShip.getY() && invaderSpaceShipShoot.isAlive()){
            if(goodSpaceShip.isCollition(this.invaderSpaceShipShoot)){
                this.invaderSpaceShipShoot.setAlive(false);
                this.goodSpaceShip.setAlive(false);
                createExplosion(true,false);
            }
        }

        //Shield collisions
        if(shield.isAlive()){

            //Collision shield       vs invader ship shoot
            if(invaderSpaceShipShoot.getBottom()>shield.getY() && invaderSpaceShipShoot.isAlive()){
                if(shield.isCollision(invaderSpaceShipShoot)){
                    this.invaderSpaceShipShoot.setAlive(false);
                }
            }

            //Collision shield       vs good ship shoot
            if(goodSpaceShipShoot.isAlive()){
                if(shield.isCollision(goodSpaceShipShoot)){
                    this.goodSpaceShipShoot.setAlive(false);
                }
            }

            //Collision shield       vs invader fleet
            if(shield.isCollision2(invaderSpaceFleet)){

            }
        }
    }
}

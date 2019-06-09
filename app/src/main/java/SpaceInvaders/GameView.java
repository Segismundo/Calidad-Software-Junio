package SpaceInvaders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({"WrongCall", "ViewConstructor"})
public class GameView extends SurfaceView
{
	private final MainActivity activity;
	private final Context context;
	private final GameLoopThread gameLoopThread;
	private InvaderSpaceFleet invaderSpaceFleet;
	private boolean firstStart = true;
	private Explosion explosion;
	private GoodSpaceShip goodSpaceShip;
	private Shoot goodSpaceShipShoot;
	private Shoot invaderSpaceShipShoot;
	private Shield shield;
	private long lastClick;
	private Bitmap bmpExplosion;
	private Bitmap bmpGoodShoot;
	private Bitmap bmpInvaderShoot;
	private Bitmap bmpBrick;
	private int level = 1;
	private int scoreValue = 0;
	private int scoreBest3;
	private final Paint scorePaint = new Paint();
	private final Typeface scoreTypeFace = Typeface.create("Helvetica", Typeface.BOLD);
	private int gameState = 1; // 1 WelcomeScreen; 2 Play; 3 GameOVer; 4 Next Level

	private WelcomeScreen welcomeScreen;
	private LevelTransitionScreen levelTransitionScreen;
	private BestScoresScreen bestScoresScreen;

	//Sound stuff
	private SoundPool soundPool;
	private MediaPlayer mPlayer;
	private int soundId;


	public GameView(Context context, MainActivity activity)
	{
		super(context);

		this.activity = activity;
		this.context = context;

		// Config background Music
		setBackgroundMusic();

		// Load sounds
		loadSounds();

		gameLoopThread = new GameLoopThread(this);

		setSurface();
	}

	private void setSurface()
	{
		getHolder().addCallback(new SurfaceHolder.Callback()
		{

			@Override
			public void surfaceDestroyed(SurfaceHolder holder)
			{
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry)
				{
					try
					{
						gameLoopThread.join();
						retry = false;
					}
					catch (InterruptedException ignored)
					{
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder)
			{
				loadBitmapSurface();
				createScreens();
				createGoodSpaceShip();
				createInvaderSpaceFleet(level);
				createGoodSpaceShipShoot(false);
				createInvaderSpaceShipShoot(false);
				createExplosion(false, false);
				createShield();

				startGameLoop();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
			{
			}
		});
	}

	private void startGameLoop()
	{
		gameLoopThread.setRunning(true);
		if (firstStart)
		{
			gameLoopThread.start();
			firstStart = false;
		}
	}


	private void loadBitmapSurface()
	{
		bmpExplosion = BitmapFactory.decodeResource(getResources(), R.drawable.explosion);
		bmpGoodShoot = BitmapFactory.decodeResource(getResources(), R.drawable.goodspaceshipshoot);
		bmpInvaderShoot = BitmapFactory.decodeResource(getResources(), R.drawable.invaderspaceshipshoot);
		bmpBrick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
	}

	private void loadSounds()
	{
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		soundId = soundPool.load(getContext(), R.raw.puaj, 1);
	}

	private void setBackgroundMusic()
	{
		if (mPlayer == null)
		{
			mPlayer = MediaPlayer.create(getContext(), R.raw.music);
			mPlayer.setLooping(true);
		}
	}

	private void createScreens()
	{
		this.welcomeScreen = new WelcomeScreen(this);
		this.levelTransitionScreen = new LevelTransitionScreen(this);
		this.bestScoresScreen = new BestScoresScreen(this, context);
		SharedPreferences bestScores = context.getSharedPreferences("BestScores", Context.MODE_PRIVATE);
		scoreBest3 = bestScores.getInt("bestScore3", 0);
	}

	// Creates a good ship
	private void createGoodSpaceShip()
	{
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.goodspaceship);
		this.goodSpaceShip = new GoodSpaceShip(this, bmp, 1, 13, context);
	}

	// Creates an invader ship
	private void createInvaderSpaceFleet(int level)
	{
		int landingHeight = goodSpaceShip.getHeight();
		this.invaderSpaceFleet = new InvaderSpaceFleet(this, this.getHeight() - landingHeight, level);
	}

	//Creates a good ship shoot
	private void createGoodSpaceShipShoot(boolean alive)
	{
		int spriteColumns = 2;
		int spriteRows = 1;

		int bmpGoodShootSize1 = goodSpaceShip.getWidth() / 2;
		int bmpGoodShootSize2 = (bmpGoodShoot.getWidth() / spriteColumns) / 2;

		int locationX = goodSpaceShip.getX() + bmpGoodShootSize1 - bmpGoodShootSize2;
		int locationY = goodSpaceShip.getY();
		int xSpeed = goodSpaceShip.getXSpeed();
		this.goodSpaceShipShoot = new Shoot(this, bmpGoodShoot, spriteRows, spriteColumns, locationX, locationY, xSpeed, false, alive);
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

	//Creates a explosion
	private void createExplosion(boolean alive, boolean invaderShip)
	{
		int spriteColumns = 25;
		int spriteRows = 1;
		int locationX;
		int locationY;

		if (invaderShip)
		{
			int locationX1 = goodSpaceShipShoot.getWidth() / 2;
			int locationX2 = (bmpExplosion.getWidth() / spriteColumns) / 2;
			locationX = goodSpaceShipShoot.getX() + locationX1 - locationX2;
			locationY = goodSpaceShipShoot.getY() - goodSpaceShipShoot.getHeight() / 2;
		}
		else
		{
			locationX = goodSpaceShip.getX();
			locationY = goodSpaceShip.getY();
		}

		explosion = new Explosion(this, bmpExplosion, spriteRows, spriteColumns, locationX, locationY, alive);
	}

	private void createShield()
	{
		int horizontalNumOfBrick = 8;
		int verticalNumOfBrick = 4;
		int spriteColumns = 4;
		int numberOfWalls = 3;

		int wallWidth = bmpBrick.getWidth() / spriteColumns * horizontalNumOfBrick;
		int separation = (this.getWidth() - wallWidth) / (numberOfWalls - 1);

		int xIni = 0;
		int yIni = goodSpaceShip.getY() - bmpBrick.getWidth() - 10;

		for (int i = 0; i < numberOfWalls; i++)
		{
			shield = new Shield(this, bmpBrick, spriteColumns, horizontalNumOfBrick, verticalNumOfBrick, xIni, yIni, numberOfWalls, separation);
		}
	}

	private void drawScore(Canvas canvas)
	{
		scorePaint.setTypeface(scoreTypeFace);
		scorePaint.setColor(Color.WHITE);
		scorePaint.setTextSize(30);
		canvas.drawText("Score: " + scoreValue + "| Level: " + level, 0, 30, scorePaint);
	}

	private void checkCollisions()
	{

		//Collision good ship shoot vs invader fleet
		checkCollisionShipVsInvader();

		//Collision good ship shoot vs invader ship shoot
		checkCollisionShipShotVsInvaderShoot();

		//Collision good ship       vs invader ship shoot
		checkCollisionShipVsInvaderShoot();

		//Shield collisions
		checkShieldsCollisions();
	}

	private void checkShieldsCollisions()
	{
		if (shield.isAlive())
		{

			//Collision shield       vs invader ship shoot
			checkCollisionShieldVsInvaderShoot();

			//Collision shield       vs good ship shoot
			checkCollisionShieldVsShipShoot();
		}
	}

	private void checkCollisionShieldVsShipShoot()
	{
		if (goodSpaceShipShoot.isAlive())
		{
			if (shield.isCollision(goodSpaceShipShoot))
			{
				this.goodSpaceShipShoot.setAlive(false);
			}
		}
	}

	private void checkCollisionShieldVsInvaderShoot()
	{
		if (invaderSpaceShipShoot.getBottom() > shield.getY() && invaderSpaceShipShoot.isAlive())
		{
			if (shield.isCollision(invaderSpaceShipShoot))
			{
				this.invaderSpaceShipShoot.setAlive(false);
			}
		}
	}

	private void checkCollisionShipVsInvaderShoot()
	{
		if (invaderSpaceShipShoot.getBottom() > goodSpaceShip.getY() && invaderSpaceShipShoot.isAlive())
		{
			if (goodSpaceShip.isCollition(this.invaderSpaceShipShoot))
			{
				this.invaderSpaceShipShoot.setAlive(false);
				this.goodSpaceShip.setAlive(false);
				createExplosion(true, false);
			}
		}
	}

	private void checkCollisionShipShotVsInvaderShoot()
	{
		if (goodSpaceShipShoot.isAlive() && invaderSpaceShipShoot.isAlive())
		{
			if (invaderSpaceShipShoot.isCollition(this.goodSpaceShipShoot))
			{
				this.invaderSpaceShipShoot.setAlive(false);
				this.goodSpaceShipShoot.setAlive(false);
			}
		}
	}

	private void checkCollisionShipVsInvader()
	{
		if (goodSpaceShipShoot.isAlive())
		{

			if (invaderSpaceFleet.isCollision(this.goodSpaceShipShoot))
			{
				scoreValue = scoreValue + invaderSpaceFleet.getPoints();
				goodSpaceShipShoot.setAlive(false);
				createExplosion(true, true);
			}
		}
	}

	private void drawGameScreen(Canvas canvas)
	{
		shipAlive(canvas);

		drawFleet(canvas);

		drawShipShoot(canvas);

		drawInvaderShoot(canvas);

		shieldAlive(canvas);

		drawScore(canvas);

		checkCollisions();

		explosionAlive(canvas);

		checkGameOver();

		checkNextLevel();
	}

	private void drawFleet(Canvas canvas)
	{
		this.invaderSpaceFleet.onDraw(canvas);
	}

	private void shipAlive(Canvas canvas)
	{
		if (!this.goodSpaceShip.isAlive())
		{
			return;
		}
		this.goodSpaceShip.onDraw(canvas);
	}

	private void checkNextLevel()
	{
		if (invaderSpaceFleet.allInvadersDestroyed())
		{
			level = level + 1;
			gameState = 4;
		}
	}

	private void checkGameOver()
	{
		if (invaderSpaceFleet.invaderArrive() || !goodSpaceShip.isAlive())
		{
			gameState = 3;
		}
	}

	private void shieldAlive(Canvas canvas)
	{
		if (!shield.isAlive())
		{
			return;
		}
		shield.onDraw(canvas);
	}

	private void explosionAlive(Canvas canvas)
	{
		if (!explosion.isAlive())
		{
			return;
		}
		explosion.onDraw(canvas);
	}

	private void drawInvaderShoot(Canvas canvas)
	{
		if (this.invaderSpaceShipShoot.isAlive())
		{
			this.invaderSpaceShipShoot.onDraw(canvas);
		}
		else if (Math.random() < 0.1)
		{
			createInvaderSpaceShipShoot(true);
		}
	}

	private void drawShipShoot(Canvas canvas)
	{
		if (this.goodSpaceShipShoot.isAlive())
		{
			this.goodSpaceShipShoot.onDraw(canvas);
		}
	}


	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.drawColor(Color.BLACK);

		switch (gameState)
		{
			case 1: //Welcome Screen
				welcomeScreen.draw(canvas);
				break;
			case 2: //Play game
				drawGameScreen(canvas);
				break;
			case 3: //Game Over, (if best score -> enter name)
				if (scoreValue > scoreBest3)
				{
					activity.runOnUiThread(new Runnable()
					{
						public void run()
						{
							bestScoresScreen.showInput(scoreValue);
						}
					});
				}
				gameState = 5;
				break;
			case 4: //NextLevel
				levelTransitionScreen.draw(canvas, level);
				break;
			case 5://Scores
				bestScoresScreen.draw(canvas);
				break;
			default:
				gameState = 1;
				break;
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if ((System.currentTimeMillis() - lastClick > 300))
		{
			lastClick = System.currentTimeMillis();

			synchronized (getHolder())
			{

				switch (gameState)
				{

					case 1://Welcome Screen
						gameState = 2;
						break;
					case 2:// Play game
						if (!goodSpaceShipShoot.isAlive())
						{
							createGoodSpaceShipShoot(true);
							soundPool.play(soundId, 1, 1, 0, 0, 1);
						}
						break;
					case 3://Enter name
						gameState = 5;
						break;
					case 4: //Next Level transition
						gameState = 2;
						createGoodSpaceShip();
						createInvaderSpaceFleet(level);
						createShield();
						createGoodSpaceShipShoot(false);
						createInvaderSpaceShipShoot(false);
						break;
					case 5:// Scores
						gameState = 1;
						level = 1;
						scoreValue = 0;
						createScreens();
						createGoodSpaceShip();
						createInvaderSpaceFleet(level);
						createShield();
						createGoodSpaceShipShoot(false);
						createInvaderSpaceShipShoot(false);
						break;
					default:
						gameState = 1;
						break;
				}
			}
		}
		return true;
	}

	// Music functions
	public void startMusicPlayer()
	{
		mPlayer.start();
	}

	public void pauseMusicPlayer()
	{
		mPlayer.pause();
	}

	public void releaseMusicPlayer()
	{
		mPlayer.release();
		soundPool.release();
	}

	public void stopMusicPlayer()
	{
		mPlayer.stop();
	}
}
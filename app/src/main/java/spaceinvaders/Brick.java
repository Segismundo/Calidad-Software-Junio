package spaceinvaders;

import android.graphics.Bitmap;

public class Brick extends Sprite{

	public Brick(){
		this.x = 20;
		this.y = 15;
		this.alive = true;
		setWidth(1);
		setHeight(1);
	}
	public Brick(GameView gameView, Bitmap bmp, int bmpRows, int bmpColumns, int x, int y) {
		super(gameView, bmp, bmpRows, bmpColumns);
		this.x = x;
		this.y = y;
		this.alive = true;
	}

	@Override
	protected void update() {}
	
	@Override
	protected int getNextAnimationRow() {
		return 0;
	}
	
	@Override
	protected int getNextAnimationColumn() {
		return 0;
	}

	public void  degrade() {	
		currentFrameColumn = currentFrameColumn+1;
		if (currentFrameColumn == bmpColumns) {
			this.alive=false;
		}
	}
}

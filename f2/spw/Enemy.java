package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int X_TO_FADE = 200;
	public static final int X_TO_DIE = 0;
	
	private int step = 12;
	private boolean alive = true;
	
	public Enemy(int x, int y) {
		super(x, y, 10, 5);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(x > X_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(x - X_TO_DIE)/(X_TO_FADE - X_TO_DIE)));
		}
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		x -= step;
		if(x < X_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}
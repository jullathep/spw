package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 8;
	int step_fall = 2;
	int step_up = 20;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 700 - width)
			x = 700 - width;
	}
	
	public void fall(int direction){
		y += (step_fall * direction);
		if(y < 0)
			y = 0;
		if(y > 340)
			y = 340;
	}
	
	public void up(int direction){
		y += (step_up * direction);
		if(y < 0)
			y = 0;
		if(y > 340)
			y = 340;
	}

}

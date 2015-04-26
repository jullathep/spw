package f2.spw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends Sprite{

    private BufferedImage image;
	int step = 8;
	int step_fall = 2;
	int step_up = 20;
	
    public ImagePanel(int x, int y, int width, int height, String path) {
        super(x, y, width, height);
		try{
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics2D g) {
		
	
		Graphics2D g2 = (Graphics2D)g;
 		g2.drawImage(image, x, y, width, height,null);
		
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
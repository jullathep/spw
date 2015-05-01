package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	private BufferedImage image;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(700, 400, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLUE);
		try{
			image = ImageIO.read(getClass().getResourceAsStream("Image/bg2.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 700, 400);
		big.drawImage(image,0,0,null);
		big.setColor(Color.BLACK);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}
	
	public void dieOver(GameReporter reporter){
		big.setColor(Color.BLACK);
		big.drawString(String.format("%s", reporter.getOver()), 300, 200);
		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
	}
	
	public void playerWin(GameReporter reporter, int index){
		big.setColor(Color.BLACK);
		if(index == 1)
			big.drawString(String.format("%s", reporter.p1Winr()), 300, 200);
		else if(index ==2)
			big.drawString(String.format("%s", reporter.p2Winr()), 300, 200);
		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}

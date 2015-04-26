package f2.spw;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyListener implements ActionListener{
	private int mode;
	
	
	public MyListener(int mode){
		this.mode = mode; 
	}
	
	public void actionPerformed(ActionEvent e){
		if(mode == 1){
			JFrame frame2 = new JFrame("Singleplayer");
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setSize(700, 400);
			frame2.getContentPane().setLayout(new BorderLayout());

			SpaceShip v = new SpaceShip(50, 180, 20, 20);
			ImagePanel robotOne = new ImagePanel(50, 180, 30, 30,"Image/robot1.png");
			GamePanel gp = new GamePanel();
			GameEngine engine = new GameEngine(gp, robotOne);
			frame2.addKeyListener(engine);
			frame2.getContentPane().add(gp, BorderLayout.CENTER);
			frame2.setVisible(true);
			engine.start();
		}
		else if(mode == 2){
			JFrame frame2 = new JFrame("Multiplayer");
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setSize(700, 400);
			frame2.getContentPane().setLayout(new BorderLayout());

			//SpaceShip v = new SpaceShip(50, 180, 20, 20);
			ImagePanel robotOne = new ImagePanel(50, 180, 30, 30, "Image/robot1.png");
			//SpaceShip v2 = new SpaceShip(600, 180, 20, 20);
			ImagePanel robotTwo = new ImagePanel(600, 180, 30, 30, "Image/robot2.png");
			GamePanel gp = new GamePanel();
			GameMulti multi = new GameMulti(gp, robotOne, robotTwo);
			frame2.addKeyListener(multi);
			frame2.getContentPane().add(gp, BorderLayout.CENTER);
			frame2.setVisible(true);
			multi.start();
		}
	    else if(mode == 3){
			System.out.println("About");
		}
	}
}
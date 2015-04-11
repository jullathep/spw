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
			JFrame frame2 = new JFrame("Space War");
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setSize(700, 400);
			frame2.getContentPane().setLayout(new BorderLayout());

			SpaceShip v = new SpaceShip(50, 180, 20, 20);
			GamePanel gp = new GamePanel();
			GameEngine engine = new GameEngine(gp, v);
			frame2.addKeyListener(engine);
			frame2.getContentPane().add(gp, BorderLayout.CENTER);
			frame2.setVisible(true);
			engine.start();
		}
		else if(mode == 2){
			System.out.println("Multiplayer");
		}
	    else if(mode == 3){
			System.out.println("About");
		}
	}
}
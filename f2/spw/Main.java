package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.GridLayout;	
public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setSize(200, 400);
		
 
        JButton button = new JButton(" >> Singleplayer <<");
        //Add action listener to button
        button.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
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
        });  
		
		JButton button2 = new JButton(" >> Mutiplayer <<");
        //Add action listener to button
        button2.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the Mutiplayer");
            }
        }); 
		
		JButton button3 = new JButton(" >> About <<");
        //Add action listener to button
        button3.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the About");
            }
        }); 
		
 		frame.setLayout(new GridLayout(3, 0));
        frame.getContentPane().add(button , BorderLayout.NORTH);
        frame.getContentPane().add(button2, BorderLayout.CENTER);
		frame.getContentPane().add(button3, BorderLayout.CENTER);
        frame.setVisible(true);
	}
}

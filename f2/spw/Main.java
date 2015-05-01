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
		MyListener ml = new MyListener(1);
		button.addActionListener(ml);
		
		JButton button2 = new JButton(" >> Mutiplayer <<");
		MyListener ml2 = new MyListener(2);
		button2.addActionListener(ml2);
		
		
 		frame.setLayout(new GridLayout(2, 0));
        frame.getContentPane().add(button , BorderLayout.NORTH);
        frame.getContentPane().add(button2, BorderLayout.CENTER);
        frame.setVisible(true);
	}
}

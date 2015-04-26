package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	
	private ImagePanel robotOne;
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	private double easy = 0.1;

	private int dash = 0;
	private long score_dash = 7000;
	
	public GameEngine(GamePanel gp, ImagePanel robotOne) {
		this.gp = gp;
		//this.v = v;		
		
		this.robotOne = robotOne;
		
		//gp.sprites.add(v);
		gp.sprites.add(robotOne);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	
	
	private void generateEnemy(){
		Enemy e = new Enemy( 600, (int)(Math.random()*390));
		if(dash == 1)
			e.ItemDash();
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateItem(){
		Item f = new Item( 600, (int)(Math.random()*390));
		gp.sprites.add(f);
		items.add(f);
	}
	
	
	
	private void process(){
		robotOne.fall(1);
		
		if(score > score_dash)
			dash = 0;
		
		
		if(dash == 1){
			generateEnemy();
		}
		else if(Math.random() < difficulty){
			generateEnemy();
		}
		
		
		if(Math.random() < easy){
			generateItem();
		}
		
		
		Iterator<Enemy> e_iter = enemies.iterator();
		
		Iterator<Item> f_iter = items.iterator();
		
		while(e_iter.hasNext() || f_iter.hasNext()){
			if(e_iter.hasNext()){
				Enemy e = e_iter.next();
				e.proceed();
				if(!e.isAlive()){
					e_iter.remove();
					gp.sprites.remove(e);
					score += 100;
				}
			}
			if(f_iter.hasNext()){
				Item f = f_iter.next();
				f.proceed();
				if(!f.isAlive()){
					f_iter.remove();
					gp.sprites.remove(f);
				}
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = robotOne.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double fr;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(dash == 1)
				e.ItemDash();

				
			if(er.intersects(vr)){
				if(dash == 0){
					die();
					gp.dieOver(this);
				}
				return;
			}
		}
		
		for(Item f : items){
			fr = f.getRectangle();
			if(fr.intersects(vr)){
				f.getItem();
				dash = 1;
				endDash();
				return;
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	
	public void endDash(){
		score_dash = score + score_dash;
	}
		   
		   
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			robotOne.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			robotOne.move(1);
			break;
		case KeyEvent.VK_A:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			robotOne.up(-1);
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	public String getOver(){
		return "Game Over";
	}
	
	public String p1Winr(){
		return "P1 Win";
	}
	
	public String p2Winr(){
		return "P2 Win";
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}

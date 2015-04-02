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
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	private double easy = 0.5;
	
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
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
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateItem(){
		Item f = new Item( 600, (int)(Math.random()*390));
		gp.sprites.add(f);
		items.add(f);
	}
	
	
	
	private void process(){
		v.fall(1);
		
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		if(Math.random() > easy){
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
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double fr;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
		
		for(Item f : items){
			fr = f.getRectangle();
			if(fr.intersects(vr)){
				f.getItem();
				return;
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			v.up(-1);
			break;
		}
	}

	public long getScore(){
		return score;
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

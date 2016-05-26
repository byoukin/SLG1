package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

public class Startup extends Canvas implements Runnable{
	public static final int WIDTH = 1250;
	public static final int HEIGHT = 700;
	public static final String TITLE = "Pixel Strategy";
	private StartMenu menu;
	private Thread thread;
	private GameMap map1;
	private Boolean run = false;
	private Cursor cursor;
	private BufferedImage bi;
	private Player currentPlayer;
	private Status status;
	
	private enum PHASE{
		TITLE,
		SELECT,
		GAME,
		STATUS
	};
	
	private enum SELECTED{
		PLAYER,
		ENEMY,
		MAP
	};
	
	private PHASE phase;
	private SELECTED selected;
	private static final long serialVersionUID = -52208218690144130L;
	
	
	public Startup(){
		try {
			map1 = new GameMap ("bin/main/maps/stage1.txt", "bin/main/maps/char1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void start(){
		if (run)
			return;
		run = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void exit(){
		if (!run)
			return;
		run = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
		
	}
	
	public void init(){
		addKeyListener(new Keyboard(this));
		menu = new StartMenu();
		cursor = new Cursor();
		bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		phase = PHASE.TITLE;
		selected = SELECTED.MAP;
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final int FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / FPS;
		double delta = 0;
//		int ticks = 0;
//		int fps = 0;
//		long timer = System.currentTimeMillis();
		while (run) {
			long now = System.nanoTime();
			delta += (now - lastTime) / (double) OPTIMAL_TIME;
			lastTime = now;
			if (delta >= 1){
				update();
				delta--;
//				ticks++;
			}
			render();
//			fps++;
//			if(System.currentTimeMillis() - timer > 1000){
//				timer+= 1000;
//				System.out.println(ticks + " " + fps);
//				ticks = 0;
//				fps = 0;
//			}
			
		}
		exit();
	}
	
	public static void main(String args[]) throws IOException{
		Startup start = new Startup();	
		
		start.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//		su.setMaximumSize(new Dimension(2560, 1440));
//		su.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame(start.TITLE);
		frame.add(start);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		System.out.println(start.getWidth() + " " + start.getHeight());
		start.start();
		
	}

	public void update(){
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		////draw here
		g.drawImage(bi, 0, 0, getWidth(),getHeight(),this);
		if (phase == PHASE.TITLE){
			menu.paintTitle(g);
		}else if (phase == PHASE.STATUS && status != null){
			status.paintStatus(g);
		}else if (phase == PHASE.GAME){
			map1.paint(g);
			cursor.render(g);
		}
		g.dispose();
		bs.show();
		
	}
	
	public void keyPressed(KeyEvent e){
		if (phase == PHASE.TITLE){
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_ENTER:
					phase = PHASE.GAME;
			}
		}else if(phase == PHASE.STATUS){
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_R:
					phase = PHASE.GAME;
			}
		}else if (phase == PHASE.GAME){
			int x = cursor.getX();
			int y = cursor.getY();
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_P:
					phase = PHASE.TITLE;
					break;
				case KeyEvent.VK_RIGHT:
					if(x + 100 <= WIDTH && ((!map1.isOccupied(x+50, y) && selected == SELECTED.PLAYER) || selected == SELECTED.MAP))
						x = cursor.setX(x + 50);				
					break;
				case KeyEvent.VK_LEFT:
					if(x - 50 >= 0 && ((!map1.isOccupied(x-50, y) && selected == SELECTED.PLAYER) || selected == SELECTED.MAP))
						x = cursor.setX(x - 50);
					break;
				case KeyEvent.VK_UP:
					if(y - 50 >= 0 && ((!map1.isOccupied(x, y-50) && selected == SELECTED.PLAYER) || selected == SELECTED.MAP))
						y = cursor.setY(y - 50);
					break;
				case KeyEvent.VK_DOWN:
					if(y + 100 <= HEIGHT && ((!map1.isOccupied(x, y+50) && selected == SELECTED.PLAYER) || selected == SELECTED.MAP))
						y = cursor.setY(y + 50);
					break;
				case KeyEvent.VK_Z:
					if(currentPlayer == null && map1.isOccupied(x, y)){
						currentPlayer = map1.getPlayers()[cursor.getX()/50][cursor.getY()/50];
						if (currentPlayer.side())
							selected = SELECTED.PLAYER;
						else
							currentPlayer = null;
					}					
					break;
				case KeyEvent.VK_X:
					if(selected == SELECTED.PLAYER || selected == SELECTED.ENEMY){
						selected = SELECTED.MAP;
						currentPlayer = null;
					}	
					break;
				case KeyEvent.VK_R:
					if(currentPlayer == null && map1.isOccupied(x, y) && selected == SELECTED.MAP && phase == PHASE.GAME){
						phase = PHASE.STATUS;
						status = new Status(map1.getPlayers()[cursor.getX()/50][cursor.getY()/50]);
					}
					break;
			}
			if (selected == SELECTED.PLAYER && currentPlayer != null && currentPlayer.side()){
				int tempX = currentPlayer.getX();
				int tempY = currentPlayer.getY();
				currentPlayer.setX(x);
				currentPlayer.setY(y);
				
				map1.getPlayers()[x/50][y/50] = new Hero(x, y, currentPlayer.getName(), currentPlayer.side());
				if (tempX != x || tempY != y){
					map1.getPlayers()[tempX/50][tempY/50] = null;
				}		
			}
				
		}
		
	}
		
	public void keyReleased(KeyEvent e){
	}

}

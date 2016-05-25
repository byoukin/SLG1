package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

public class Startup extends Canvas implements Runnable{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 16 * 9;
//	public static final int SCALE = 2;
	public static final String TITLE = "Pixel Strategy";
	private StartMenu menu;
	private Thread thread;
	private GameMap map1;
	private Boolean run = false;
	private Cursor cursor;
	private BufferedImage bi;
	
	private enum PHASE{
		TITLE,
		SELECT,
		GAME
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
//		ImageLoader loader = new ImageLoader();
//		try{
//			hero = loader.loadImage("/main/UI/hero.png");
//		}catch(IOException e){
//			e.printStackTrace();
//		}
		
/*		try {
			p = new Player(5,5,"hero");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
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
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
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
		}else if (phase == PHASE.GAME){
			map1.paint(g);
			cursor.render(g);
		}
		////
		
		g.dispose();
		bs.show();
		
	}
	
	
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			exit();
		if (phase == PHASE.TITLE){
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_ENTER:
					phase = PHASE.GAME;
			}
		}else if (phase == PHASE.GAME){
			int x = cursor.getX();
			int y = cursor.getY();
			int key = e.getKeyCode();
			Player currentPlayer = map1.getPlayers()[cursor.getX()/50][cursor.getY()/50];
			switch(key){
				case KeyEvent.VK_RIGHT:
					if(x + 100 <= WIDTH)
						x = cursor.setX(x + 50);				
					break;
				case KeyEvent.VK_LEFT:
					if(x - 50 >= 5)
						x = cursor.setX(x - 50);
					break;
				case KeyEvent.VK_UP:
					if(y - 50 >= 5)
						y = cursor.setY(y - 50);
					break;
				case KeyEvent.VK_DOWN:
					if(y + 100 <= HEIGHT)
						y = cursor.setY(y + 50);
					break;
				case KeyEvent.VK_Z:
					if(currentPlayer != null)
						selected = SELECTED.PLAYER;
					break;
				case KeyEvent.VK_X:
					if(selected == SELECTED.PLAYER)
						selected = SELECTED.MAP;
					break;
			}
			if (selected == SELECTED.PLAYER && currentPlayer != null){
				System.out.println(currentPlayer.getX() + " " + currentPlayer.getY());
				map1.updatePlayerLocation(currentPlayer.getX(), currentPlayer.getY(), x, y);
			}
				
		}
		
	}
		
	public void keyReleased(KeyEvent e){
	}

}

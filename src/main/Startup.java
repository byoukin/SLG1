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
	private StartMenu sm = new StartMenu();
	private Thread thread;
	private GameMap map1;
	private Boolean run = false;
	private Cursor cursor = new Cursor();
	private BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private enum PHASE{
		TITLE,
		SELECT,
		GAME
	};
	private PHASE phase = PHASE.TITLE;
	private static final long serialVersionUID = -52208218690144130L;
	
	
	public Startup(){
		try {
			map1 = new GameMap ("bin/main/maps/stage1.txt");
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
		System.exit(1);
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
		Startup su = new Startup();	
		
		su.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//		su.setMaximumSize(new Dimension(2560, 1440));
//		su.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame jf = new JFrame(su.TITLE);
		jf.add(su);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		su.start();
		
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
			sm.paintTitle(g);
		}else if (phase == PHASE.GAME){
			map1.paint(g);
			cursor.render(g);
		}
		////
		
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
		}else if (phase == PHASE.GAME){
			int key = e.getKeyCode();
			int x = cursor.getX();
			int y = cursor.getY();
			switch(key){
				case KeyEvent.VK_RIGHT:
					if(cursor.getX() + 100 <= WIDTH)
						cursor.setX(cursor.getX() + 50);
					break;
				case KeyEvent.VK_LEFT:
					if(cursor.getX() - 50 >= 5)
						cursor.setX(cursor.getX() - 50);
					break;
				case KeyEvent.VK_UP:
					if(cursor.getY() - 50 >= 5)
						cursor.setY(cursor.getY() - 50);
					break;
				case KeyEvent.VK_DOWN:
					if(cursor.getY() + 100 <= HEIGHT)
						cursor.setY(cursor.getY() + 50);
					
					break;
			}	
		}
		
	}
		
	public void keyReleased(KeyEvent e){
	}

}

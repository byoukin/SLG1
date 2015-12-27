package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JFrame;

public class Startup extends Canvas implements Runnable{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 16 * 9;
//	public static final int SCALE = 2;
	public static final String TITLE = "Game Title";
	private GameMap map1 = new GameMap ("bin/main/maps/stage1.txt");
	private StartMenu sm = new StartMenu();
	private Thread thread;
	private Boolean run = false;
	private enum PHASE{
		TITLE,
		SELECT,
		GAME
	};
	private PHASE phase = PHASE.TITLE;
	private static final long serialVersionUID = -52208218690144130L;
	public Startup() throws IOException{}
	
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
	public void run(){
		long lastTime = System.nanoTime();
		final int FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / FPS;
		double delta = 0;
		while (run) {
			long now = System.nanoTime();
			delta += (now - lastTime) / (double) OPTIMAL_TIME;
			lastTime = now;
			if (delta >= 1){
				update();
				delta--;
			}
			render();
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

	public void paint(Graphics g){
		if (phase == PHASE.GAME){
			map1.paint(g);
		}else if (phase == PHASE.TITLE){
			sm.paint(g);
		}
	//	repaint();
	}
	
	public void update(){
	}
	
	public void render(){
		
	}

}

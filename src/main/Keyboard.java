package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter{
	
	private Startup s = new Startup();
	
	public Keyboard(Startup s){
		this.s = s;
	}
	
	public void keyPressed(KeyEvent e){
		s.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		s.keyReleased(e);
	}
}

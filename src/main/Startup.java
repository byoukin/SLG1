package main;

import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class Startup extends JFrame{
	private GameMap map1 = new GameMap ("bin/main/maps/stage1.txt");
	
	private static final long serialVersionUID = -52208218690144130L;
	public Startup() throws IOException{}
	public static void main(String args[]) throws IOException{	
		new Startup();	
	}

	public void paint(Graphics g){
		repaint();
	}
	
	public void update(Graphics g){
		paint(g);
	}
}

package main;

import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class Startup extends JFrame{
	
	private static final long serialVersionUID = -52208218690144130L;
	public Startup() throws IOException{
		init();
	}
	public static void main(String args[]) throws IOException{
		new Startup();
	}
	
	public void init() throws IOException {

		setSize(2560, 1440);
		setBackground(Color.black);
		setVisible(true);
		
//		map1 = new GameMap("bin/main/maps/stage1.txt");
	}

//	private GameMap map1;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image a1 = tk.getImage(this.getClass().getResource("/main/cells/mountain.png"));
	public void paint(Graphics g){
		g.drawImage(a1, 250, 250, this);
		repaint();
	}
	
	public void Menu(Graphics g){
		
	}
	
	public void update(Graphics g){
		paint(g);
	}

}

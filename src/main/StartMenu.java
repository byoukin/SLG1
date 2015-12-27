package main;

import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

class StartMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image title = tk.getImage(this.getClass().getResource("/main/UI/title.png"));
//	private Image select = tk.getImage(this.getClass().getResource("/main/UI/select.png"));

	public StartMenu(){
	}
	
	public synchronized int event(KeyEvent e){
		return e.getKeyCode();
	}
	
	public void paintTitle(Graphics g){
		Graphics2D g2d = (Graphics2D) g.create();
	//	Font tFont = new Font("Arial", Font.BOLD, 45);
	//	g2d.setFont(tFont);
		g2d.drawImage(title,0,0,this);
	}
	
	public void paintSelect(Graphics g){
	}
	
	public void paint(Graphics g){
		paintTitle(g);
		repaint();
	}
}

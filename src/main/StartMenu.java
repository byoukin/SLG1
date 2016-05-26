package main;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

class StartMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image title = tk.getImage(this.getClass().getResource("/main/UI/title.gif"));
//	private Image select = tk.getImage(this.getClass().getResource("/main/UI/select.png"));

	public StartMenu(){
	}
	
	public void paintTitle(Graphics g){
		Graphics2D g2d = (Graphics2D) g.create();
		Font tFont = new Font("Arial", Font.BOLD, 45);
		g2d.setFont(tFont);
		g2d.drawImage(title,0,0,this);
		g2d.drawString("PRESS ENTER", 475, 550);
	}
	
	public void paintSelect(Graphics g){
	}
	
	public void paint(Graphics g){
		paintTitle(g);
		repaint();
	}
}

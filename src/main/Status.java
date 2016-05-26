package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Status extends JFrame {
	
	private Player player;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image background = tk.getImage(this.getClass().getResource("/main/UI/status.png"));
	
	public Status(Player p){
		player = p;
	}

	
	public void paintStatus(Graphics g){
		Graphics2D g2d = (Graphics2D) g.create();
		Font font1 = new Font("Arial", Font.ITALIC, 45);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 45);
		g2d.setFont(font2);
		g2d.drawImage(background,0,0,this);
		g2d.setColor(new Color(224,255,255));
		g2d.fillRect(15, 15, 300, 300);
		g2d.setColor(Color.black);
		g2d.drawImage(scale(player.getImage(), BufferedImage.TYPE_INT_ARGB, 300, 300, 6, 6), 15, 15, this);
		g2d.drawString(player.getCharName(), 325, 60);
		g2d.drawString("LV " + player.getLvl() + " " + player.getClassName(), 325, 110);
		g2d.drawString("EXP: " + player.getExp() + " / 100", 325, 160);
		g2d.drawString("HP " + player.getHp(), 30, 360);
		g2d.drawString("STR " + player.getStr(), 30, 410);
		g2d.drawString("SKL " + player.getSkl(), 30, 460);
		g2d.drawString("SPD " + player.getSpd(), 30, 510);
		g2d.drawString("DEF " + player.getDef(), 30, 560);
		
	}
	
	public void paint(Graphics g){
		paintStatus(g);
		repaint();
	}
	
	public BufferedImage scale(BufferedImage img, int type, int dWidth, int dHeight, int x, int y){
		BufferedImage rimg = null;
		if (img != null){
			rimg = new BufferedImage(dWidth, dHeight, type);
			Graphics2D g = rimg.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(x, y);
			g.drawRenderedImage(img, at);
		}
		return rimg;
	}
}

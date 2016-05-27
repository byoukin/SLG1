package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Options extends JFrame{
	private Player player;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image attack = tk.getImage(this.getClass().getResource("/main/UI/att.png"));
	private Image move = tk.getImage(this.getClass().getResource("/main/UI/move.png"));
	private Image finish = tk.getImage(this.getClass().getResource("/main/UI/finish.png"));
	private boolean canAttack, canMove;
	private int pX, x, y, size;
	
	public Options(Player p){
		canAttack = true;
		canMove = true;
		if (p != null){
			pX = p.getX();
			if (pX < 625)
				x = 1000;
			else
				x = 50;
			y = 50;
		}
		size = 1;
		
	}
	
	public void showOption(Graphics g){
		if (canMove){
			g.drawImage(move, x, y, this);
			if (canAttack)
				g.drawImage(attack, x, y+50, this);
			g.drawImage(finish, x, y+100, this);
			size = 3;
		}else if(canAttack){
			g.drawImage(attack, x, y, this);
			g.drawImage(finish, x, y+50, this);
			size = 2;
		}else
			g.drawImage(finish, x, y, this);
	}
	
	public void paint(Graphics g){
		showOption(g);
		repaint();
	}
	
	public boolean canMove(){
		return canMove;
	}
	
	public boolean canAttack(){
		return canAttack;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public int length(){return size;}
}

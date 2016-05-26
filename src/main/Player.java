package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {
	
	protected int x;
	protected int y;
	protected ImageLoader loader = new ImageLoader();
	protected BufferedImage player;
	protected String name, className, charName;
	protected boolean side;
	protected int hp, str, def, skl, spd, mov, lvl, exp;
	protected boolean sword, lance, axe, bow;
	
	public Player(int x, int y, String name, boolean side){
		this.x = x;
		this.y = y;
		this.name = name;
		this.side = side;
		String path = "/main/UI/" + name + ".png";
		try{
			player = loader.loadImage(path);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void update(){
	}
	
	public boolean side(){
		return side;
	}
	
	public void render(Graphics g){
		g.drawImage(player, x, y, null);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int n){
		x = n;
	}
	
	public void setY(int n){
		y = n;
	}
	
	public boolean selected(){
		return false;
	}
	
	public String getName(){
		return name;
	}
	
	public String getClassName(){
		return className;
	}
	
	public void setExp(int e){
		exp += e;
	}
	
	public int getHp(){return hp;}
	public int getStr(){return str;}
	public int getDef(){return def;}
	public int getSkl(){return skl;}
	public int getSpd(){return spd;}
	public int getMov(){return mov;}
	public int getLvl(){return lvl;}
	public int getExp(){return exp;}
	public String getCharName(){return charName;}
	public BufferedImage getImage(){
		return player;
	}
}


package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {
	
	private int x;
	private int y;
	private ImageLoader loader = new ImageLoader();
	private BufferedImage player;
	
	public Player(int x, int y, String name) throws IOException{
		this.x = x;
		this.y = y;
		String path = "/main/UI/" + name + ".png";
		player = loader.loadImage(path);
	}
	
	public void update(){
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
}


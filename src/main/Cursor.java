package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Cursor {
	private int x = 5;
	private int y = 5;
	private ImageLoader loader = new ImageLoader();
	private BufferedImage cursor;
	
	public Cursor(){
		try {
			cursor = loader.loadImage("/main/UI/cursor.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
	}
	
	public void render(Graphics g){
		g.drawImage(cursor, (int)x, (int)y, null);
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
}

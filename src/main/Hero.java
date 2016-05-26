package main;

import java.awt.image.BufferedImage;

public class Hero extends Player {

	int x;
	int y;
	String name;
	boolean side;
	//Specs of Hero
	int hp = 25;
	int str = 7;
	int def = 5;
	int skl = 8;
	int spd = 8;
	int mov = 5;
	int lvl = 1;
	int exp = 0;
	//Weapon list
	boolean sword = true;
	boolean lance = false;
	boolean axe = false;
	boolean bow = false;
	
	
	public Hero(int x, int y, String name, boolean side) {
		super(x, y, name, side);
	}
	
	
	
	public int getHp(){return hp;}
	public int getStr(){return str;}
	public int getDef(){return def;}
	public int getSkl(){return skl;}
	public int getSpd(){return spd;}
	public int getMov(){return mov;}
	public int getLvl(){return lvl;}
	public int getExp(){return exp;}
	public String getCharName(){return "Chill Candle S";}
	public String getClassName(){return "Ranger";}
}

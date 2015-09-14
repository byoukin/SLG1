package main;

import java.awt.Image;
public class Hero extends Chars {

	//Specs of Hero
	int hp = 25;
	int str = 7;
	int def = 5;
	int skl = 8;
	int spd = 8;
	int mov = 5;
	//Weapon list
	boolean sword = true;
	boolean axe = false;
	boolean bow = false;
	
	
	public Hero(String name, String className, Image icon, int side, int x, int y, String[] inventory) {
		super(name, className, icon, side, x, y, inventory);
	}
}

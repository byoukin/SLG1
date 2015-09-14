package main;

import java.awt.Image;
public class Fighter extends Chars {

	//Specs of Fighter
	int hp = 30;
	int str = 9;
	int def = 2;
	int skl = 4;
	int spd = 5;
	int mov = 5;
	//Weapon list
	boolean sword = false;
	boolean axe = true;
	boolean bow = false;
	
	
	public Fighter(String name, String className, Image icon, int side, int x, int y, String[] inventory) {
		super(name, className, icon, side, x, y, inventory);
	}
}

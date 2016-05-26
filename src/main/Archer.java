package main;

import java.awt.Image;
public class Archer extends Player {

	//Specs of Archer
	int hp = 25;
	int str = 7;
	int def = 5;
	int skl = 8;
	int spd = 8;
	int mov = 5;
	//Weapon list
	boolean sword = false;
	boolean axe = false;
	boolean bow = true;
	boolean side;
	
	
	public Archer(int x, int y, String name, boolean side) {
		super(x, y, name, side);
	}
}

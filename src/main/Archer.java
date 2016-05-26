package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
public class Archer extends Player {

	int x;
	int y;
	String name;
	boolean side;
	//Specs of Archer
	int hp, str, def, skl, spd, mov;
	//Weapon list
	boolean sword = false;
	boolean axe = false;
	boolean bow = true;
	
	
	
	public Archer(int x, int y, String name, boolean side) {
		super(x, y, name, side);
	}
}

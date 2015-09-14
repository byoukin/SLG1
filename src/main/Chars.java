package main;

import java.awt.Image;

public class Chars {
	
	//Character Name
	String name;
	//Character class
	String className;
	//Icon representing character
	Image icon;
	//Character side
	int side;
	//Character starting x-coordinate
	int x;
	//Character starting y-coordinate
	int y;
	//Indicating this character has acted or not
	boolean acted;
	//Inventory of the character
	String[] inventory;
	//Specs of characters
	int hp, str, def, skl, spd, mov;
	//Weapon can be used
	boolean sword, axe, bow;
	//Weapon currently using:
	Weapon weapon;
	
	public Chars(String name, String className, Image icon, int side, int x, int y, String[] inventory){
		this.name = name;
		this.className = className;
		this.icon = icon;
		this.side = side;
		this.x = x;
		this.y = y;
		this.inventory = inventory;
	}
	
	public String getName() {return name;}
	public String getClassName() {return className;}
	public Image getIcon() {return icon;}
	public int getSide() {return side;}
	public int getX() {return x;}
	public int getY() {return y;}
	public String[] getInv() {return inventory;}
	public void setWeapon() {sword = axe = bow = false;}
	public int getHp(){return hp;}
	public int getStr(){return str;}
	public int getDef(){return def;}
	public int getSkl(){return skl;}
	public int getSpd(){return spd;}
	public int getMov(){return mov;}
	public void CalcDamage(boolean success, int atk){
		if (success)
			hp = hp + def - atk;
	}
	public boolean isDead(){if (hp == 0) return true; return false;}
	public boolean Attacking(int spd) {
		int rate = (int)(Math.random()*100+1);
		int hit = skl*4 + weapon.getHit()  - spd*4;
		if (hit >= rate) return true;
		else return false;
	}
	public boolean isActed(){return acted;}
	
}

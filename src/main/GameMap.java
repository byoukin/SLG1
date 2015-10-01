package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class GameMap extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Toolkit kit = Toolkit.getDefaultToolkit();
	public GameMap(String fileName) throws IOException{
	//	readFile(fileName);
	}
	
	public String[] readFile(String fileName) throws IOException {
		File file = new File(fileName);
		String mapCell[] = new String[20];
		int count = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try{
			String read = null;
			while ((read = reader.readLine()) != null){
				mapCell[count] = read;
				count++;
				System.out.println(read);
			}
			reader.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return mapCell;
	}
	
	public void drawMap(Graphics g){
		Image abc = kit.getImage(this.getClass().getResource("src/main/cells/mountain.bmp"));
		g.drawImage(abc, 250, 250, this);
	}
		
}

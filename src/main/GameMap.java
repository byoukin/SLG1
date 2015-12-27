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
	private String stage = null;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Image mountain = tk.getImage(this.getClass().getResource("/main/cells/mountain.png"));
	private Image ground = tk.getImage(this.getClass().getResource("/main/cells/ground.png"));
	private Image forest = tk.getImage(this.getClass().getResource("/main/cells/forest.png"));
	private Image river = tk.getImage(this.getClass().getResource("/main/cells/river.png"));
	private Image castle = tk.getImage(this.getClass().getResource("/main/cells/castle.png"));
	private Image[] cells = null;
	
	public GameMap(String fileName) throws IOException{
		stage = fileName;
	}
	
	public void paint(Graphics g){
		try {
			drawMap(getStage(),g);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	public String getStage(){
		return stage;
	}
	
	public String[] readFile(String fileName) throws IOException {
		File file = new File(fileName);
		String mapCell[] = new String[10];
		int count = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try{
			String read = null;
			while ((read = reader.readLine()) != null){
				mapCell[count] = read;
				count++;
			}
			reader.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return mapCell;
	}
	
	public void drawMap(String maps, Graphics g) throws IOException{
		String[] map = readFile(maps);
		cells = new Image[map.length*map[0].length()];
		int count = 0;
		if (map != null){
			for (int i = 0; i < map.length; i++){
				for (int j = 0; j < map[i].length(); j++){
					Image img = null;
					if (map[i].charAt(j) == 'G')
						img = ground;
					else if (map[i].charAt(j) == 'M')
						img = mountain;
					else if (map[i].charAt(j) == 'R')
						img = river;
					
					g.drawImage(img, 100+50*j, 100+50*i, this);
					cells[i*(map.length)+j] = img;
				}
			}
		}
	}
	
	public Image[] getCells(){
		return cells;
	}
}

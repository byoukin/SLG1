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
	
	public GameMap(String fileName) throws IOException{
		setSize(2560,1440);
		setVisible(true);
		
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
			//	System.out.println(read);
			}
			reader.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return mapCell;
	}
	
	public void drawMap(String maps, Graphics g) throws IOException{
		String[] map = readFile(maps);
		int count = 0;
		if (map != null){
			for (int i = 0; i < map.length; i++){
				System.out.print(map[i]==null);
				for (int j = 0; j < map[i].length(); j++){
				//	System.out.println(count++);
					if (map[i].charAt(j) == 'G')
						g.drawImage(ground, 100+50*j, 100+50*i, this);
					else if (map[i].charAt(j) == 'M')
						g.drawImage(mountain, 100+50*j, 100+50*i, this);
					else if (map[i].charAt(j) == 'R')
						g.drawImage(river, 100+50*j, 100+50*i, this);
				}
			}
		}	
	}
}

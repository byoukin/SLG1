package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class GameMap extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private String stage = null;
	private ImageLoader loader = new ImageLoader();
	private BufferedImage mountain = loader.loadImage("/main/cells/mountain.png");
	private BufferedImage ground = loader.loadImage("/main/cells/ground.png");
	private BufferedImage forest = loader.loadImage("/main/cells/forest.png");
	private BufferedImage river = loader.loadImage("/main/cells/river.png");
	private BufferedImage castle = loader.loadImage("/main/cells/castle.png");
	private BufferedImage[][] cells = null;
	private Player[][] players = null;
	public GameMap(String fileName) throws IOException{
		stage = fileName;
	}
	
	public void paint(Graphics g){
		try {
			drawMap(stage,g);
			drawPlayer(stage,g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getStage(){
		return stage;
	}
	
	public String[] readFile(String fileName) throws IOException {
		File file = new File(fileName);
		String mapCell[] = new String[14];
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
		cells = new BufferedImage[map.length][map[0].length()];
		int count = 0;
		if (map != null){
			for (int i = 0; i < map.length; i++){
				for (int j = 0; j < map[i].length(); j++){
					BufferedImage img = null;
					char c = map[i].charAt(j);
					switch (c){
					case 'G':
						img = ground;
						break;
					case 'M':
						img = mountain;
						break;
					case 'R':
						img = river;
						break;
					}
					cells[i][j] = img;
					g.drawImage(cells[i][j], 5+50*j, 5+50*i, this);
				}
			}
		}
	}
	
	public void drawPlayer(String playerList, Graphics g) throws IOException{
		players = new Player[cells[0].length][cells.length];
		for (int i = 0; i < players.length; i++)
			for (int j = 0; j < players[0].length; j++){
				players[i][j] = null;
			}
		players[0][2] = new Player(5, 105, "hero");
		players[0][2].render(g);	
		players[1][2] = new Player(55, 105, "hero");
		players[1][2].render(g);
	}
	
	public BufferedImage[][] getCells(){
		return cells;
	}
	
	public Player[][] getPlayers(){
		return players;
	}
	
	public boolean isOccupied(int x, int y){
		if (players[x/50][y/50] != null)
			return true;
		return false;
	}
}

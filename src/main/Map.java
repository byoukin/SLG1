package main;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {
	
	public Map(String fileName) throws IOException{
		readFile(fileName);
	}
	
	private static void readFile(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try{
			String read = null;
			while ((read = reader.readLine()) != null){
				System.out.println(read);
			}
			reader.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
		
}

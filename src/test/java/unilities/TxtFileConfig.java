package unilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TxtFileConfig {
	
	static String data;
	public static String Read(String filepath) {
		 try {
		      File myObj = new File(filepath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data = myReader.nextLine();
//		        System.out.println(data);
		        
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 return data;
		
	}

	
}

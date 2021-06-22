package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.codehaus.groovy.syntax.ParserException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileConfig {
	
	public void Read() {
		
		JSONParser parser = new JSONParser();
	
	
		try {
			Object obj = parser.parse(new FileReader("FilePath"));
			JSONObject jsonObject = (JSONObject) obj;
//			String name = (String) jsonObject.get("name");
//			System.out.println(name);

		}
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
//		catch (ParseException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
	}
		

}

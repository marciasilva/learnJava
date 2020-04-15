package files;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Helper {
	
	public static Reader openReader(String fileName) {
		Reader reader = null;
		try {
			reader = new FileReader("./resources/" + fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return reader;
		
	}

	public static Writer openWriter(String newFileName) { 
		Writer writer = null;
		try {
			writer = new FileWriter("./resources/" + newFileName);
		} catch (IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
		return writer;

	}

}

package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utils {
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	public Utils() {
		
	}
	
	public static ArrayList<String> loadLines(String path) {
		Path file = FileSystems.getDefault().getPath(Paths.get(".").toAbsolutePath().normalize().toString(), path);
		try (InputStream in = Files.newInputStream(file); BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			 ArrayList<String> lines = new ArrayList<String>(10);
			 String line = null;
			 while ((line = reader.readLine()) != null) {
				 lines.add(line);
			 }
			 return lines;
		 } catch (IOException e) {
			 System.err.println(e);
			 return null;
		}
	}

}

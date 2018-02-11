package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseRegistry {
	
	private String dbName;
	private HashMap <String, String[]> courseMap;
	
	public CourseRegistry(String db) {
		this.dbName = db;
		this.courseMap = new HashMap<String, String[]>();
		this.loadRegistry();
	}
	
	private void loadRegistry() {
		ArrayList<String> lines = Utils.loadLines(dbName);
		lines.forEach(line -> {
			String[] data = line.split(";");
			String course = data[0];
			String[] modules = data[1].split(",");
			this.courseMap.put(course, modules);
		});
	}
}

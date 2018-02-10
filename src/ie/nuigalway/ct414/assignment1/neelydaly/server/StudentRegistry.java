package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentRegistry {
	private HashMap<Integer, Student> registeredStudents;
	private String dbName;
	
	public StudentRegistry(String db) {
		this.dbName = db;
		this.registeredStudents = new HashMap<Integer, Student>();
		this.loadRegistry();
	}
	
	private void loadRegistry() {
		ArrayList<String> lines = Utils.loadLines(dbName);
		lines.forEach(line -> {
			String[] student = line.split(";");
			int id = Integer.parseInt(student[0]);
			String pwd = student[1];
			String course = student[2];
			String[] modules = student[3].split(",");
			this.registeredStudents.put(id, new Student(id, pwd, course, modules));
		});
	}
	
	protected void register(Student s) {
		this.registeredStudents.put(s.getID(), s);
	}
	
	protected Student getStudent(int id) {
		return this.registeredStudents.get(id);
	}
}

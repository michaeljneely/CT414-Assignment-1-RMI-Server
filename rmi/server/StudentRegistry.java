package server;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentRegistry {
	private HashMap<String, Student> registeredStudents;
	private String dbName;
	
	public StudentRegistry(String db) {
		this.dbName = db;
		this.registeredStudents = new HashMap<String, Student>();
		this.loadRegistry();
	}
	
	private void loadRegistry() {
		ArrayList<String> lines = Utils.loadLines(dbName);
		lines.forEach(line -> {
			String[] student = line.split(";");
			String id = student[0];
			String pwd = student[1];
			String course = student[2];
			this.registeredStudents.put(id, new Student(id, pwd, course));
		});
	}
	
	protected void register(Student s) {
		this.registeredStudents.put(s.getID(), s);
	}
	
	protected Student getStudent(String id) {
		return this.registeredStudents.get(id);
	}
	
	protected boolean exists(String id) {
		return this.registeredStudents.containsKey(id);
	}
}
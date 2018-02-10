package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StudentRegistry {
	private Set<Student> registeredStudents;
	private String dbName;
	
	public StudentRegistry(String db) {
		this.dbName = db;
		this.registeredStudents = new HashSet<Student>();
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
			this.registeredStudents.add(new Student(id, pwd, course, modules));
		});
	}
	
	protected void register(Student s) {
		this.registeredStudents.add(s);
	}
	
	protected Student getStudent(int id) {
		for(Student s: this.registeredStudents) {
			if (s.getID() == id) {
				return s;
			}
		}
		return null;
	}
}

package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.util.Set;

public class StudentRegistry {
	private Set<Student> registeredStudents;
	
	public StudentRegistry() {
		
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

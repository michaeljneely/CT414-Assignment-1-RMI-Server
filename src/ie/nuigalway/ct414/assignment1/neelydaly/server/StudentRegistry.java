package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.util.Set;

public class StudentRegistry {
	private Set<Student> registeredStudents;
	
	public StudentRegistry() {
		
	}
	
	protected void register(Student s) {
		this.registeredStudents.add(s);
	}
	
	protected boolean login(int id, String pwd) {
		for(Student s: registeredStudents) {
			if (s.getID() == id && s.getPassword().equals(pwd)) {
				
			}
		}
		return false;
	}
}

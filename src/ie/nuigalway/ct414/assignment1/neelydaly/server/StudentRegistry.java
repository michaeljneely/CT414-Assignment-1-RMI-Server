package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class StudentRegistry {
	private Set<Student> registeredStudents;
	private String path;
	
	public StudentRegistry(String path) {
		this.path = path;
		this.registeredStudents = new HashSet<Student>();
		this.loadRegistry();
	}
	
	private void loadRegistry() {
		Path file = FileSystems.getDefault().getPath(Paths.get(".").toAbsolutePath().normalize().toString(), "students.txt");
		try (InputStream in = Files.newInputStream(file); BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			 String line = null;
			 while ((line = reader.readLine()) != null) {
				 String[] student = line.split(";");
				 int id = Integer.parseInt(student[0]);
				 String pwd = student[1];
				 String course = student[2];
				 String[] modules = student[3].split(",");
				 this.registeredStudents.add(new Student(id, pwd, course, modules));
			 }
		 } catch (IOException e) {
			 System.err.println(e);
		}
		
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

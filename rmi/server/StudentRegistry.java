package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;


public class StudentRegistry {
	private HashMap<String, Student> registeredStudents;
	private String dbName;
	
	public StudentRegistry(String initialDB, String studentDB) {
		this.dbName = studentDB;
		try {
			this.loadRegistry();
		} catch (Exception e) {
			this.registeredStudents = new HashMap<String, Student>();
			if (e.getMessage().equals("No Saved Students!")) {
				System.out.println("Loading from backup");
				this.initializeStudents(initialDB);
			}
		}
	}
	
	private void loadRegistry()throws Exception, IOException, ClassNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(this.dbName));  
		if (br.readLine() == null) {
    		throw new Exception("No Saved Students!");
		}
		FileInputStream fis = new FileInputStream(this.dbName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		this.registeredStudents = (HashMap<String, Student>) ois.readObject();
		fis.close();
		ois.close();
	}
	
	protected void writeRegistry() throws IOException {
		FileOutputStream fos = new FileOutputStream(this.dbName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this.registeredStudents);
		oos.flush();
		oos.close();
		fos.flush();
		fos.close();
	}
	
	private void initializeStudents(String initialDB) {
		ArrayList<String> lines = Utils.loadLines(initialDB);
		lines.forEach(line -> {
			String[] data = line.split(";");
			String studentID = data[0];
			String course = data[1];
			this.registeredStudents.put(studentID, new Student(studentID, course));
		});
	}
	
	protected void recordAssessment(String studentID, MultipleChoiceAssessment assessment) {
		this.registeredStudents.get(studentID).addCompletedAssessment(assessment);
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

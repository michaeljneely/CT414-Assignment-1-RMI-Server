package ie.nuigalway.ct414.assignment1.neelydaly.server;

public class Student {

	private String StudentID;
	private String password;
	private String course;
	private boolean isLoggedIn;
	private String token;
	
	public Student(String id, String password, String course) {
		this.StudentID = id;
		this.password = password;
		this.course = course;
		this.isLoggedIn = false;
		this.token = null;
	}
	
	protected String getID() {
		return this.StudentID;
	}
	
	protected String getPassword() {
		return this.password;
	}
	
	protected boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	protected void logon() {
		this.isLoggedIn = true;
	}
	
	protected void logoff() {
		this.isLoggedIn = false;
	}
	
	protected String getToken() {
		return this.token;
	}
	
	protected String getCourse() {
		return this.course;
	}
	
	protected void setToken(String t) {
		this.token = t;
	}
}
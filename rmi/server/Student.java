package server;

public class Student {

	private String StudentID;
	private String res;
	private String course;
	private boolean isLoggedIn;
	private String token;
	
	public Student(String id, String course, String res) {
		this.StudentID = id;
		this.res = res;
		this.course = course;
		this.isLoggedIn = false;
		this.token = null;
	}
	
	protected String getID() {
		return this.StudentID;
	}
	
	protected String getRes() {
		return this.res;
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
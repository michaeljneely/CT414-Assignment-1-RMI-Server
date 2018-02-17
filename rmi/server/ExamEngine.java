
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.net.InetAddress;
import java.util.HashMap;
import ct414.*;

public class ExamEngine implements ExamServer {

	private LogonServer logonServer;
	private StudentRegistry students;
	private AssessmentRegistry assessments;
	private CourseRegistry courses;

	public ExamEngine(String logonDB, String initialStudentDB, String studentDB, String assessmentDB, String courseDB) {
		super();
		this.logonServer = new LogonServer(logonDB);
		this.students = new StudentRegistry(initialStudentDB, studentDB);
		this.assessments = new AssessmentRegistry(assessmentDB);
		this.courses = new CourseRegistry(courseDB);
	}

	// Returns encoded temporary access token
	@Override
	public String login(String studentID, String password) throws  UnauthorizedAccess, RemoteException {
		if (this.students.exists(studentID)) {
			return this.logonServer.login(studentID, password);
		} else {
			throw new UnauthorizedAccess("Logon Details Incorrect");
		}
	}

	// Return a summary list of Assessments currently available for this studentID
	@Override
	public List<String> getAvailableSummary(String token) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(token)) {
			String studentID = this.logonServer.getStudentIDFromToken(token);
			String[] modules = this.courses.getModulesByCourse(this.students.getStudent(studentID).getCourse());
			ArrayList<MultipleChoiceAssessment> studentAssessments = this.students.getStudent(studentID).getCompletedAssessments();
			ArrayList<MultipleChoiceAssessment> applicableAssessments = this.assessments.getAssessmentsForModules(modules);
			HashMap<String, String> summary = new HashMap<String,String>();
			for (MultipleChoiceAssessment mcq: studentAssessments){
				summary.put(mcq.getAssociatedID(), new String(mcq.getAssociatedID() + "-" + mcq.getStatus() + "-" + mcq.getMarks() + "%-" + mcq.getInformation()));
			}
			for (MultipleChoiceAssessment mcq: applicableAssessments){
				if (!summary.containsKey(mcq.getAssociatedID())){
					summary.put(mcq.getAssociatedID(), new String(mcq.getAssociatedID() + "-" + mcq.getStatus() + "-Not Submitted-" + mcq.getInformation()));
				}
			}
			return new ArrayList<String>(summary.values());
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	// Return an Assessment object available for this studentID
	@Override
	public Assessment getAssessmentByID(String token, String assessmentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(token)) {
			return this.assessments.getAssessmentByID(assessmentID);
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	// Submit a completed assessment
	@Override
	public String submitAssessment(String token, Assessment completed) throws  UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(token)) {
			String studentID = this.logonServer.getStudentIDFromToken(token);
			MultipleChoiceAssessment completedAssessment = this.assessments.markAssessment( (MultipleChoiceAssessment) completed);
			String marks = completedAssessment.getMarks();
			this.students.recordAssessment(studentID, (MultipleChoiceAssessment) completed);
			try {
				this.students.writeRegistry();
			} catch (Exception e){
				marks = "Error Recording Assessment";
			}
			return marks;
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "ExamServer";
			String logonDB = "access.txt";
			String initialStudentDB = "initial-students.txt";
			String studentDB = "students.txt";
			String assessmentDB = "assessments.txt";
			String courseDB = "courses.txt";
			ExamServer engine = new ExamEngine(logonDB, initialStudentDB, studentDB, assessmentDB, courseDB);
			ExamServer stub = (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("ExamServer", stub);
			System.out.println("ExamEngine bound at" + InetAddress.getLocalHost());
		} catch (Exception e) {
			System.err.println("ExamEngine exception:");
			e.printStackTrace();
		}
	}
}


package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import ct414.*;

public class ExamEngine implements ExamServer {

	private LogonServer logonServer;
	private StudentRegistry students;
	private AssessmentRegistry assessments;
	private CourseRegistry courses;

	public ExamEngine(String logonDB, String studentDB, String assessmentDB, String courseDB) {
		super();
		this.logonServer = new LogonServer(logonDB);
		this.assessments = new AssessmentRegistry(assessmentDB);
		this.courses = new CourseRegistry(courseDB);
		this.students = new StudentRegistry(studentDB);
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
	public List<AssessmentDetails> getAvailableSummary(String token, String studentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(studentID, token)) {
			String[] modules = this.courses.getModulesByCourse(this.students.getStudent(studentID).getCourse());
			return new ArrayList<AssessmentDetails>(this.assessments.getAssessmentsForModules(modules));
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	// Return an Assessment object available for this studentID
	@Override
	public Assessment getAssessmentByID(String token, String studentID, String assessmentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(studentID, token)) {
			return this.assessments.getAssessmentByID(assessmentID);
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	// Submit a completed assessment
	@Override
	public void submitAssessment(String token, String studentID, Assessment completed) throws  UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(studentID, token)) {
			this.assessments.submitAssessment( (MultipleChoiceAssessment) completed);
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	public static void main(String[] args) {
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		}
		try {
			String name = "ExamServer";
//			String logonDB = System.getProperty("LOGON_DB");
//			String studentDB = System.getProperty("STUDENT_DB");
//			String courseDB = System.getProperty("COURSE_DB");
//			String assessmentDB = System.getProperty("ASSESSMENT_DB");
			String logonDB = "access.txt";
			String studentDB = "students.txt";
			String courseDB = "courses.txt";
			String assessmentDB = "assessments.txt";
			ExamServer engine = new ExamEngine(logonDB, studentDB, assessmentDB, courseDB);
			ExamServer stub =
					(ExamServer) UnicastRemoteObject.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry("127.0.0.1");
			registry.rebind(name, stub);
			System.out.println("ExamEngine bound");
		} catch (Exception e) {
			System.err.println("ExamEngine exception:");
			e.printStackTrace();
		}
	}
}

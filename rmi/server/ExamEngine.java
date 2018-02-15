
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.net.InetAddress;

import ct414.*;

public class ExamEngine implements ExamServer {

	private LogonServer logonServer;
	private StudentRegistry students;
	private AssessmentRegistry assessments;
	private CourseRegistry courses;
	// static Registry registry;

	public ExamEngine(String logonDB, String studentDB, String assessmentDB, String courseDB) {
		super();
		this.logonServer = new LogonServer(logonDB);
		this.students = new StudentRegistry(studentDB);
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
	public List<AssessmentDetails> getAvailableSummary(String token) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(token)) {
			String studentID = this.logonServer.getStudentIDFromToken(token);
			String[] modules = this.courses.getModulesByCourse(this.students.getStudent(studentID).getCourse());
			ArrayList<AssessmentDetails> details = new ArrayList<AssessmentDetails>();
			ArrayList<MultipleChoiceAssessmentDetails> mcqDetails 
				= new ArrayList<MultipleChoiceAssessmentDetails>(this.assessments.getAssessmentsForModules(modules));
			for(MultipleChoiceAssessmentDetails mcqDetail : mcqDetails){
				details.add((AssessmentDetails) mcqDetail);
			}
			return details;
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
			return this.assessments.submitAssessment( (MultipleChoiceAssessment) completed);
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
			String assessmentDB = "assessments.txt";
			String courseDB = "courses.txt";
			ExamServer engine = new ExamEngine(logonDB, studentDB, assessmentDB, courseDB);
			ExamServer stub = (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
			// registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("ExamServer", stub);
			System.out.println("ExamEngine bound at" + InetAddress.getLocalHost());
		} catch (Exception e) {
			System.err.println("ExamEngine exception:");
			e.printStackTrace();
		}
	}
}

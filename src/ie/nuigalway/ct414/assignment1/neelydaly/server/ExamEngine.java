
package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import ie.nuigalway.ct414.assignment1.neelydaly.common.*;

public class ExamEngine implements ExamServer {

	private AssessmentRegistry assessmentRegistry;
	private StudentRegistry studentRegistry;
	private LogonServer logonServer;

	public ExamEngine() {
		super();
		this.logonServer = new LogonServer("students.txt");
		this.studentRegistry = new StudentRegistry("students.txt");
		this.assessmentRegistry = new AssessmentRegistry("assessments.txt");
	}

	// Returns encoded temporary access token
	@Override
	public String login(String studentid, String password) throws  UnauthorizedAccess, RemoteException {
		return this.logonServer.login(studentid, password);
	}

	// Return a summary list of Assessments currently available for this studentid
	@Override
	public List<Pair<String,String>> getAvailableSummary(String token, String studentid) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		return this.assessmentRegistry.getAssessmentDetailsForStudent(studentid);
	}

	// Return an Assessment object associated with a particular course code
	@Override
	public Assessment getAssessmentByID(String token, String assessmentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		return this.assessmentRegistry.getAssessmentByID(assessmentID);
	}

	// Submit a completed assessment
	@Override
	public void submitAssessment(String token, Assessment completed) throws  UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		this.assessmentRegistry.submitAssessment( (MultipleChoiceAssessment) completed);
	}

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "ExamServer";
			ExamServer engine = new ExamEngine();
			ExamServer stub =
					(ExamServer) UnicastRemoteObject.exportObject(engine, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("ExamEngine bound");
		} catch (Exception e) {
			System.err.println("ExamEngine exception:");
			e.printStackTrace();
		}
	}
}

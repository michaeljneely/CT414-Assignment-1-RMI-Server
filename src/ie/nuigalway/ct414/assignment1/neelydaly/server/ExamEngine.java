
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
	private LogonServer logonServer;

	public ExamEngine() {
		super();
		this.logonServer = new LogonServer("students.txt");
		this.assessmentRegistry = new AssessmentRegistry("assessments.txt");
	}

	// Returns encoded temporary access token
	@Override
	public String login(String studentID, String password) throws  UnauthorizedAccess, RemoteException {
		return this.logonServer.login(studentID, password);
	}

	// Return a summary list of Assessments currently available for this studentID
	@Override
	public List<Pair<String,String>> getAvailableSummary(String token, String studentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(studentID, token)) {
			return this.assessmentRegistry.getAssessmentDetailsForStudent(studentID);
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	// Return an Assessment object available for this studentID
	@Override
	public Assessment getAssessmentByID(String token, String studentID, String assessmentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(studentID, token)) {
			return this.assessmentRegistry.getAssessmentByID(assessmentID);
		} else {
			throw new UnauthorizedAccess("");
		}
	}

	// Submit a completed assessment
	@Override
	public void submitAssessment(String token, String studentID, Assessment completed) throws  UnauthorizedAccess, NoMatchingAssessment, RemoteException {
		if (this.logonServer.isTokenValid(studentID, token)) {
			this.assessmentRegistry.submitAssessment( (MultipleChoiceAssessment) completed);
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

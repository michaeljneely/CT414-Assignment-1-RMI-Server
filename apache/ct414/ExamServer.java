package ct414;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ExamServer extends Remote {

	// Return an encoded access token that allows access to the server for some time period
	public String login(String studentid, String password) throws  UnauthorizedAccess, RemoteException;

	// Return a Summary of assessments available for the given student ID
	public List<String> getAvailableSummary(String token) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Return an Assessment object by its unique ID
	public Assessment getAssessmentByID(String token, String assessmentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Submit a completed assessment
	public String submitAssessment(String token, Assessment completed) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException;

}
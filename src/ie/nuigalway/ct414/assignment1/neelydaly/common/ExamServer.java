package ie.nuigalway.ct414.assignment1.neelydaly.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ExamServer extends Remote {

	// Return an encoded access token that allows access to the server for some time period
	public String login(String studentid, String password) throws  UnauthorizedAccess, RemoteException;

	// Return a Summary of assessments available for the given student ID
	public List<AssessmentDetails> getAvailableSummary(String token, String studentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Return an Assessment object by its unique ID
	public Assessment getAssessmentByID(String token, String studentID, String assessmentID) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Submit a completed assessment
	public void submitAssessment(String token, String studentID, Assessment completed) throws UnauthorizedAccess, NoMatchingAssessment, RemoteException;

}
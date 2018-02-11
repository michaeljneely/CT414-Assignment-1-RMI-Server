// ExamServer.java

package ie.nuigalway.ct414.assignment1.neelydaly.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public interface ExamServer extends Remote {

	// Return an access token that allows access to the server for some time period
	public String login(String studentid, String password) throws 
		UnauthorizedAccess, RemoteException;

	// Return a summary list of Assessments currently available for this studentid (Pair - <AssessmentID, AssessmentInfo>)
	public List<Pair<String,String>> getAvailableSummary(String token, String studentID) throws
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Return an Assessment object associated with a particular course code
	public Assessment getAssessmentById(String token, String studentID, String assessmentID) throws
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

	// Submit a completed assessment
	public void submitAssessment(String token, String studentID, Assessment completed) throws 
		UnauthorizedAccess, NoMatchingAssessment, RemoteException;

}


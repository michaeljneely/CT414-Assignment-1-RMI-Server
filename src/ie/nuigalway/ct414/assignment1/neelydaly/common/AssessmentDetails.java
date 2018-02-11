package ie.nuigalway.ct414.assignment1.neelydaly.common;

import java.io.Serializable;

public interface AssessmentDetails extends Serializable {
	
	// get unique assessment ID
	public String getAssementID();
	
	// get information about the assignment
	public String getAssessmentInformation();
}

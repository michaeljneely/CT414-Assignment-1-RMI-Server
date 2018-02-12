package ct414;

import java.io.Serializable;

public interface AssessmentDetails extends Serializable {
	
	// get unique assessment ID
	public String getAssementID();
	
	// get information about the assignment
	public String getAssessmentInformation();
}

package server;

import ct414.*;

public class MultipleChoiceAssessmentDetails implements AssessmentDetails{

	private static final long serialVersionUID = -42924887225443210L;
	private String ID;
	private String information;

	public MultipleChoiceAssessmentDetails(String ID, String information) {
		super();
		this.ID = ID;
		this.information = information;
	}
	@Override
	public String getAssementID() {
		return this.ID;
	}

	@Override
	public String getAssessmentInformation() {
		return this.information;
	}

}

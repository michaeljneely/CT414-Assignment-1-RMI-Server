package server;

import java.util.HashMap;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

	private String StudentID;
	private String course;
	private HashMap<String, MultipleChoiceAssessment> completedAssessments;
	
	public Student(String id, String course) {
		this.StudentID = id;
		this.course = course;
		this.completedAssessments = new HashMap<String, MultipleChoiceAssessment>();
	}
	
	protected String getID() {
		return this.StudentID;
	}
	
	protected ArrayList<MultipleChoiceAssessment> getCompletedAssessments() {
		return new ArrayList<MultipleChoiceAssessment>(this.completedAssessments.values());
	}
	
	protected String getMarksForAssessment(String assessmentID) {
		return this.completedAssessments.get(assessmentID).getMarks();
	}
	
	protected void addCompletedAssessment(MultipleChoiceAssessment assessment) {
		this.completedAssessments.put(assessment.getAssociatedID(), assessment);
	}

	protected String getCourse() {
		return this.course;
	}
}
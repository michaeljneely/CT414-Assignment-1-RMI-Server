package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ie.nuigalway.ct414.assignment1.neelydaly.common.*;

public class MultipleChoiceAssessment implements Assessment {

	private static final long serialVersionUID = -6660187375794326772L;
	private int ID;
	private Date startDate;
	private Date endDate;
	private List<MultipleChoiceQuestion> questionList;

	public MultipleChoiceAssessment(int id, Date startDate, Date endDate, List<MultipleChoiceQuestion> questions) {
		this.ID = id;
		if (endDate.before(startDate)) {
			throw new IllegalArgumentException("Start Date must be before End Date.");
		}
		if (questions.isEmpty()) {
			throw new IllegalArgumentException("Assessment must contain questions.");
		}
		this.startDate = startDate;
		this.endDate = endDate;
		this.questionList = questions;
	}

	@Override
	public String getInformation() {
		return "Assessment ID: " + this.ID + " is available from " + this.startDate + " to " + this.endDate
				+ ", and has " + this.questionList.size() + " questions.";
	}

	@Override
	public Date getClosingDate() {
		return this.endDate;
	}

	@Override
	public List<Question> getQuestions() {
		
	}

	@Override
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		if (this.questionList.get(questionNumber) != null) {
			return this.questionList.get(questionNumber);
		} else throw new InvalidQuestionNumber("");
	}

	@Override
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelectedAnswer(int questionNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAssociatedID() {
		return this.ID;
	}

}

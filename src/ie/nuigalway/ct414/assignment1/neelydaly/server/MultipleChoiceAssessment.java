package ie.nuigalway.ct414.assignment1.neelydaly.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ie.nuigalway.ct414.assignment1.neelydaly.common.*;

public class MultipleChoiceAssessment implements Assessment {

	private static final long serialVersionUID = -6660187375794326772L;
	private int ID;
	private String info;
	private Date startDate;
	private Date endDate;
	private HashMap<Integer, MultipleChoiceQuestion> questionList;
	private Set<Student> eligibleStudents;

	public MultipleChoiceAssessment(int id, String info, Date startDate, Date endDate, MultipleChoiceQuestion[] questions, Student[] students) {
		this.ID = id;
		this.info = info;
		if (endDate.before(startDate)) {
			throw new IllegalArgumentException("Start Date must be before End Date.");
		}
		if (questions.length == 0) {
			throw new IllegalArgumentException("Assessment must contain questions.");
		}
		this.startDate = startDate;
		this.endDate = endDate;
		for (int i = 0; i < questions.length; i++) {
			questionList.put(i, questions[i]);
		}	
		for (int i = 0; i < students.length; i++) {
			eligibleStudents.add(students[i]);
		}
	}

	@Override
	public String getInformation() {
		return this.info;
	}

	@Override
	public Date getClosingDate() {
		return this.endDate;
	}
	
	public Date getStartingDate() {
		return this.startDate;
	}

	@Override
	public List<Question> getQuestions() {
		return new ArrayList<Question>(this.questionList.values());
	}

	@Override
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		if (this.questionList.get(questionNumber) != null) {
			return this.questionList.get(questionNumber);
		} else throw new InvalidQuestionNumber("");
	}

	@Override
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
		MultipleChoiceQuestion q = (MultipleChoiceQuestion) this.getQuestion(questionNumber);
		q.answer(optionNumber);
	}

	@Override
	public int getSelectedAnswer(int questionNumber) {
		return this.questionList.get(questionNumber).getSelectedAnswer();
	}

	@Override
	public int getAssociatedID() {
		return this.ID;
	}
	
	public boolean isElligible(int studentID) {
		for(Student s: this.eligibleStudents) {
			if (s.getID() == studentID) {
				return true;
			}
		}
		return false;
	}

}

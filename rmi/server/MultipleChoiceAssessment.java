package server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ct414.*;

public class MultipleChoiceAssessment implements Assessment {

	private static final long serialVersionUID = -6660187375794326772L;
	private String ID;
	private String info;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private HashMap<Integer, MultipleChoiceQuestion> questionList;
	private String module;

	public MultipleChoiceAssessment(String id, String module, String info, LocalDateTime startDate, LocalDateTime endDate, HashMap<Integer, MultipleChoiceQuestion> questions) {
		this.ID = id;
		this.info = info;
		this.module = module;
		if (endDate.isBefore(startDate)) {
			throw new IllegalArgumentException("Start Date must be before End Date.");
		}
		if (questions.size() == 0) {
			throw new IllegalArgumentException("Assessment must contain questions.");
		}
		this.startDate = startDate;
		this.endDate = endDate;
		this.questionList = questions;
	}

	@Override
	public String getInformation() {
		return this.info;
	}

	@Override
	public LocalDateTime getClosingDate() {
		return this.endDate;
	}
	
	public LocalDateTime getStartingDate() {
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
	public String getAssociatedID() {
		return this.ID;
	}
	
	public String getModule() {
		return this.module;
	}

}

package ie.nuigalway.ct414.assignment1.neelydaly.server;

import ct414.InvalidOptionNumber;
import ct414.Question;

public class MultipleChoiceQuestion implements Question {

	private static final long serialVersionUID = -4368950029656033594L;
	private int number;
	// question text
	private String detail;
	// answer texts
	private String[] options;
	// index of correct answer in the 'options' array
	private int correctAnswer;
	private int selectedAnswer;
	
	public MultipleChoiceQuestion(int number, String detail, String[] options, int correctAnswer) {
		this.number = number;
		this.detail = detail;
		this.options = options;
		this.correctAnswer = correctAnswer;
		this.selectedAnswer = -1;
	}

	@Override
	public int getQuestionNumber() {
		return this.number;
	}

	@Override
	public String getQuestionDetail() {
		return this.detail;
	}

	@Override
	public String[] getAnswerOptions() {
		return this.options;
	}
	
	public void answer(int answer) throws InvalidOptionNumber{
		if (answer >= 0 && answer < this.options.length) {
			this.selectedAnswer = answer;
		} else throw new InvalidOptionNumber("");
	}
	
	protected int getCorrectAnswer() {
		return this.correctAnswer;
	}

	public int getSelectedAnswer() {
		return this.selectedAnswer;
	}

}

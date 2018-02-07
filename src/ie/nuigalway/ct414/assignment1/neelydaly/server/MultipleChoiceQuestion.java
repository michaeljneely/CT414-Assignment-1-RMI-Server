package ie.nuigalway.ct414.assignment1.neelydaly.server;

import ie.nuigalway.ct414.assignment1.neelydaly.common.Question;

public class MultipleChoiceQuestion implements Question {

	private static final long serialVersionUID = -4368950029656033594L;
	private int number;
	private String question;
	private String[] options;
	private int answer;
	
	public MultipleChoiceQuestion(int number, String question, String[] options, int answer) {
		this.number = number;
		this.question = question;
		this.options = options;
		this.answer = answer;
	}

	@Override
	public int getQuestionNumber() {
		return this.number;
	}

	@Override
	public String getQuestionDetail() {
		String detail = "Question: " + this.question + "\nOptions: ";
		for(int i = 0; i < this.options.length; i++) {
			detail += "\n" + i +": " + this.options[i];
		}
		return detail;
	}

	@Override
	public String[] getAnswerOptions() {
		return this.options;
	}
	
	

}

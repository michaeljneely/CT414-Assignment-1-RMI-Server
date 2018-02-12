package ct414;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface Assessment extends Serializable {

	// Return information about the assessment	
	public String getInformation();

	// Return the final date / time for submission of completed assessment
	public LocalDateTime getClosingDate();

	// Return a list of all questions and answer options
	public List<Question> getQuestions();

	// Return one question only with answer options
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber;

	// Answer a particular question
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber;

	// Return selected answer or zero if none selected yet
	public int getSelectedAnswer(int questionNumber);

	// Return StudentID associated with this assessment object
	// This will be preset on the server before the object is downloaded
	public String getAssociatedID();

}
package ie.nuigalway.ct414.assignment1.neelydaly.server;

import ie.nuigalway.ct414.assignment1.neelydaly.common.*;

public class TestQuestions {

	public static void main(String[] args) {
		String q1Ans = "Absolute Zero";
		String[] q1Opts = {"Very Cool", "Kind of Cool", "Not Cool at all", q1Ans};	
		MultipleChoiceQuestion q1 = new MultipleChoiceQuestion(1, "How Cool is Ross?", q1Opts, 3);
		String[] a = q1.getAnswerOptions();
		System.out.println(a);
	}

}
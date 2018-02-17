package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ct414.*;
import server.ExamEngine;

import java.util.ArrayList;

public class IntegrationTest {

	private static ExamEngine engine;
	private static String token;
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	private String login(){
		try {
			return engine.login("1", "a");
		} catch (Exception e) {
			return "";
		}
	}

	@BeforeClass
	public static void setUp() {
		String logonDB = "access.txt";
		String initialStudentDB = "initial-students.txt";
		String studentDB = "students.txt";
		String courseDB = "courses.txt";
		String assessmentDB = "assessments.txt";
		engine = new ExamEngine(logonDB, initialStudentDB, studentDB, assessmentDB, courseDB);
	}

	@Test
	public void validateLogin() {
		try {
			assertEquals(login().isEmpty(), false);
		} catch (Exception e){
			fail (e.getMessage());
		}
	}

	@Test
	public void validateNonExistantStudent() throws Exception {
		exceptions.expect(UnauthorizedAccess.class);
		exceptions.expectMessage("Student Does Not Exist");
		engine.login("Incorrect", "Incorrect");
	}

	@Test
	public void validateIncorrectPassword() throws Exception {
		exceptions.expect(UnauthorizedAccess.class);
		exceptions.expectMessage("Incorrect Logon Details");
		engine.login("1", "b");
	}

	@Test
	public void validateInvalidQuestionNumber() throws Exception {
		exceptions.expect(InvalidQuestionNumber.class);
		exceptions.expectMessage("'7' is not a valid option");
		Assessment assessment = engine.getAssessmentByID(login(), "10001");
		assessment.getQuestion(7);
	}

	@Test
	public void validateInvalidOptionNumber() throws Exception {
		exceptions.expect(InvalidOptionNumber.class);
		exceptions.expectMessage("'7' is not a valid option");
		Assessment assessment = engine.getAssessmentByID(login(), "10001");
		assessment.selectAnswer(0,7);
	}

	@Test
	public void validateAssessmentDetails() {
		try {
			ArrayList<String> assessmentDetails = new ArrayList<String>(engine.getAvailableSummary(login()));
			String detail1[] = assessmentDetails.get(0).split("-"); // 10002
			String detail2[] = assessmentDetails.get(1).split("-"); // 10001
			String detail3[] = assessmentDetails.get(2).split("-"); // 10003
			assertEquals(detail1[0], "10002");
			assertEquals(detail1[1], "Active");
			assertEquals(detail1[2], "Not Submitted");
			assertEquals(detail1[3], "Real Time Systems Homework 1");
			assertEquals(detail2[0], "10001");
			assertEquals(detail2[1], "Active");
			assertEquals(detail2[2], "Not Submitted");
			assertEquals(detail2[3], "Introduction to Java Assignment 1");
			assertEquals(detail3[0], "10003");
			assertEquals(detail3[1], "Expired");
			assertEquals(detail3[2], "Not Submitted");
			assertEquals(detail3[3], "Expired HW");
		} catch (Exception e){
			fail (e.getMessage());
		}
	}
	
	@Test
	public void validateExpiredAssignment() throws Exception {
		exceptions.expect(NoMatchingAssessment.class);
		exceptions.expectMessage("Assessment Has Expired");
		Assessment expired = engine.getAssessmentByID(login(), "10003");
	}

	@Test
	public void validateNonExistantAssignment() throws Exception {
		exceptions.expect(NoMatchingAssessment.class);
		exceptions.expectMessage("Assessment Not Found");
		Assessment expired = engine.getAssessmentByID(login(), "17663");
	}

	@Test
	public void validateAssessmentSubmission() {
		try {
			Assessment assessment = engine.getAssessmentByID(login(), "10001");
			assertEquals(assessment.getQuestions().size(), 2);
			Question question1 = assessment.getQuestion(0);
			Question question2 = assessment.getQuestion(1);
			assertEquals(question1.getQuestionDetail(), "Which of these classes is not included in java.lang?");
			assertEquals(question2.getQuestionDetail(), "What does the abstract class in Java mean?");
			assessment.selectAnswer(0,2);
			assessment.selectAnswer(1,0);
			assertEquals(assessment.getSelectedAnswer(0), 2);
			assertEquals(assessment.getSelectedAnswer(1), 0);
			String result = engine.submitAssessment(login(), assessment);
			assertEquals(result, "50.0");
			ArrayList<String> assessmentDetails = new ArrayList<String>(engine.getAvailableSummary(login()));
			assertEquals(assessmentDetails.get(1).split("-")[2], "50.0%");
		} catch (Exception e){
			fail (e.getMessage());
		}
	}
}
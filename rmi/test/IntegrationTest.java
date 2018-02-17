package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ct414.*;
import server.ExamEngine;

import java.util.ArrayList;

public class IntegrationTest {

    @Test
    public void test() {
        try {
            String logonDB = "access.txt";
            String initialStudentDB = "initial-students.txt";
            String studentDB = "students.txt";
            String courseDB = "courses.txt";
            String assessmentDB = "assessments.txt";
            ExamEngine engine = new ExamEngine(logonDB, initialStudentDB, studentDB, assessmentDB, courseDB);
            String token = engine.login("1", "a");
            ArrayList<String> assessmentDetails = new ArrayList<String>(engine.getAvailableSummary(token));
            assertEquals(assessmentDetails.size(), 2);
            Assessment assessment1 = engine.getAssessmentByID(token, assessmentDetails.get(0).split("-")[0]);
            Assessment assessment2 = engine.getAssessmentByID(token, assessmentDetails.get(1).split("-")[0]);
            assertEquals(assessment1.getAssociatedID(), "10001");
            assertEquals(assessment1.getQuestions().size(), 2);
            assertEquals(assessment2.getAssociatedID(), "10002");
            assertEquals(assessment2.getQuestions().size(), 1);
            Question question11 = assessment1.getQuestion(0);
            Question question12 = assessment1.getQuestion(1);
            Question question21 = assessment2.getQuestion(0);
            assertEquals(question11.getQuestionDetail(), "Which of these classes is not included in java.lang?");
            assertEquals(question12.getQuestionDetail(), "What does the abstract class in Java mean?");
            assertEquals(question21.getQuestionDetail(), "How do hard, firm, and soft Real Time Systems differ?");
            assessment1.selectAnswer(0,2);
            assessment1.selectAnswer(1,1);
            assessment2.selectAnswer(0,0);
            assertEquals(assessment1.getSelectedAnswer(0), 2);
            assertEquals(assessment1.getSelectedAnswer(1), 1);
            String assessment1Result = engine.submitAssessment(token, assessment1);
            // String assessment2Result = engine.submitAssessment(token, assessment2);
            System.out.println(assessment1Result);
            // assertEquals(assessment2Result, "Submitted!");
        } catch(Exception e){
            fail(e.getMessage());
        }

    }
}
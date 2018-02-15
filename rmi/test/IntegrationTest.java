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
            String studentDB = "students.txt";
            String courseDB = "courses.txt";
            String assessmentDB = "assessments.txt";
            ExamEngine engine = new ExamEngine(logonDB, studentDB, assessmentDB, courseDB);
            String token = engine.login("1", "a");
            Assessment assessment1 = engine.getAssessmentByID(token, engine.getAvailableSummary(token).get(0).split("-")[0]);
            Question question1 = assessment1.getQuestion(0);
            assessment1.selectAnswer(0,2);
            String result = engine.submitAssessment(token, assessment1);
            assertEquals(assessment1.getAssociatedID(), "10001");
            assertEquals(question1.getQuestionDetail(), "Which of these classes is not included in java.lang?");
            assertEquals(assessment1.getSelectedAnswer(0), 2);
            assertEquals(result, "Submitted!");
        } catch(Exception e){
            fail();
        }

    }
}
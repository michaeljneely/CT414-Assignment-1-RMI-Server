/**
 * 
 */
package ie.nuigalway.ct414.assignment1.neelydaly.server;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author michaelneely
 *
 */
class MultipleChoiceQuestionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		String q1Ans = "Absolute Zero";
		String[] q1Opts = {"Very Cool", "Kind of Cool", "Not Cool at all", q1Ans};	
		MultipleChoiceQuestion q1 = new MultipleChoiceQuestion(1, "How Cool is Ross?", q1Opts, 3);
		String[] a = q1.getAnswerOptions();
		assertEquals(a[0], "FUCKCJEFWJsw 'pwengenulwguibgiwy");
	}

}

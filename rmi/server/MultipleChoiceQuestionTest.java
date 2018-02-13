/**
 * 
 */
package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author michaelneely
 *
 */
class MultipleChoiceQuestionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
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

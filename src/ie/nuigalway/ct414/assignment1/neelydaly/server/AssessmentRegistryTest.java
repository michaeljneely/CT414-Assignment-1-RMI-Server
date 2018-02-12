package ie.nuigalway.ct414.assignment1.neelydaly.server;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ct414.NoMatchingAssessment;

class AssessmentRegistryTest {

	AssessmentRegistry registry;
	private static final String testDB = "assessments.txt";

	@BeforeClass
	static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	static void tearDownAfterClass() throws Exception {
	}

	@Before
	void setUp() throws Exception {
		registry = new AssessmentRegistry(testDB);
	}

	@After
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		try {
			assertEquals(registry.getAssessmentByID("10001").getAssociatedID(),"10001");
		} catch (NoMatchingAssessment e) {
			fail(e.getMessage());
		}
	}

}

package ie.nuigalway.ct414.assignment1.neelydaly.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ie.nuigalway.ct414.assignment1.neelydaly.common.NoMatchingAssessment;

class AssessmentRegistryTest {

	AssessmentRegistry registry;
	private static final String testDB = "assessments.txt";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		registry = new AssessmentRegistry(testDB);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		try {
			assertEquals(registry.getAssessmentByID("10001").getAssociatedID(),"10001");
		} catch (NoMatchingAssessment e) {
			fail(e);
		}
	}

}

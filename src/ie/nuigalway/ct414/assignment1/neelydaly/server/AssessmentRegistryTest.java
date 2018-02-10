package ie.nuigalway.ct414.assignment1.neelydaly.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertEquals(registry.getAssessment(10001).getAssociatedID(),10001);
	}

}
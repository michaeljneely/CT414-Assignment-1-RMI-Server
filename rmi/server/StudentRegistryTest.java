package server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

class StudentRegistryTest {

	StudentRegistry registry;
	

	@BeforeClass
	static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	static void tearDownAfterClass() throws Exception {
	}

	@Before
	void setUp() throws Exception {
		registry = new StudentRegistry("students.txt");
	}

	@After
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertEquals(registry.getStudent("123").getPassword(), "cats");
	}

}
